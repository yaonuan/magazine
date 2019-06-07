package com.magazine.service.impl;

import com.magazine.deconstruct.AnalyseDeconstruction;
import com.magazine.deconstruct.PolicyDeconstruction;
import com.magazine.deconstruct.PolicyDeconstructionMapper;
import com.magazine.domain.ScrapyGovPolicyEntity;
import com.magazine.service.MultiThreadDeconstructSerivce;
import com.magazine.service.ScrapyGovPolicyService;
import com.magazine.utils.SerializeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/5/16
 */
@Service
public class MultiThreadDeconstructSerivceImpl implements MultiThreadDeconstructSerivce {

    private final ScrapyGovPolicyService scrapyGovPolicyService;
    private final PolicyDeconstructionMapper deconstructionMapper;
    private final AnalyseDeconstruction analyseDeconstruction;

    @Autowired
    public MultiThreadDeconstructSerivceImpl(ScrapyGovPolicyService scrapyGovPolicyService, PolicyDeconstructionMapper deconstructionMapper, AnalyseDeconstruction analyseDeconstruction) {
        this.scrapyGovPolicyService = scrapyGovPolicyService;
        this.deconstructionMapper = deconstructionMapper;
        this.analyseDeconstruction = analyseDeconstruction;
    }


    @Override
    public boolean multiDeconstruct(List<ScrapyGovPolicyEntity> list) {
//        List<ScrapyGovPolicyEntity> list = scrapyGovPolicyService.queryList();
        LinkedBlockingQueue<Runnable> objects = new LinkedBlockingQueue<>();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(6, 6, 0L, TimeUnit.MINUTES, objects);
        threadPoolExecutor.prestartAllCoreThreads();

        List<Future<String>> submits = new ArrayList<>();
        try {
            for (ScrapyGovPolicyEntity general : list) {

                Callable callable = new Callable() {
                    @Override
                    public Object call() throws Exception {
                        byte[] bytes = new byte[0];
                        ArrayList<String> vlist = analyseDeconstruction.paragraph_analyse(general.getText());
                        try {
                            bytes = SerializeUtils.serializeObject(vlist);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        PolicyDeconstruction deconstruction = new PolicyDeconstruction();
                        deconstruction.setVerbs(bytes);
                        deconstruction.setPolicyId(Long.valueOf(general.getId()));
                        deconstruction.setPolicyTitle(general.getTitle().trim());
                        //                System.out.println("线程名称：" + Thread.currentThread().getName() + "处理政策数据的id：" + general.getTitle());
                        // 判断数据库中是否已经存在
                        if (deconstructionMapper.selectByPolicyId(deconstruction.getPolicyId()) == null) {
                            deconstructionMapper.insert(deconstruction);
                        }
                        //                System.out.println(Thread.currentThread().getName() + ":" + general.getTitle());
                        return Thread.currentThread().getName() + ":" + general.getTitle();
                    }
                };
                Future<String> submit = threadPoolExecutor.submit(callable);
                submits.add(submit);
            }
        } finally {
            threadPoolExecutor.shutdown();
        }
        for (Future<String> sub : submits) {
            try {
                String s = sub.get();
                System.out.println(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public void singleDeconstruct(List<ScrapyGovPolicyEntity> list) {
        for (ScrapyGovPolicyEntity general : list) {
            byte[] bytes = new byte[0];
            ArrayList<String> vlist = analyseDeconstruction.paragraph_analyse(general.getText());
            System.out.println(general.getId() + vlist.toString());
            try {
                bytes = SerializeUtils.serializeObject(vlist);
            } catch (IOException e) {
                e.printStackTrace();
            }
            PolicyDeconstruction deconstruction = new PolicyDeconstruction();
            deconstruction.setVerbs(bytes);
            deconstruction.setPolicyId(Long.valueOf(general.getId()));
            deconstruction.setPolicyTitle(general.getTitle().trim());
            // 判断数据库中是否已经存在
            if (deconstructionMapper.selectByPolicyId(deconstruction.getPolicyId()) == null) {
                deconstructionMapper.insert(deconstruction);
            }
        }
    }
}
