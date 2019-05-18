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
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
    public boolean test() {
        List<ScrapyGovPolicyEntity> list = scrapyGovPolicyService.queryList();

        LinkedBlockingQueue<Runnable> objects = new LinkedBlockingQueue<>();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 4, 3L, TimeUnit.SECONDS, objects);
        threadPoolExecutor.prestartAllCoreThreads();

        for (ScrapyGovPolicyEntity entity : list) {
            final ScrapyGovPolicyEntity general = entity;
            Future<?> submit = threadPoolExecutor.submit(() -> {
//                byte[] bytes = new byte[0];
//                ArrayList<String> vlist = analyseDeconstruction.paragraph_analyse(general.getText());
//                try {
//                    bytes = SerializeUtils.serializeObject(vlist);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                PolicyDeconstruction deconstruction = new PolicyDeconstruction();
//                deconstruction.setVerbs(bytes);
//                deconstruction.setPolicyId(Long.valueOf(general.getId()));
//                deconstruction.setPolicyTitle(general.getTitle().trim());
//                System.out.println("线程名称：" + Thread.currentThread().getName() + "处理政策数据的id：" + general.getTitle());
//                // 判断数据库中是否已经存在
//                if (deconstructionMapper.selectByPolicyId(deconstruction.getPolicyId()) == null) {
//                    deconstructionMapper.insert(deconstruction);
//                }
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":" + general.getTitle());
            });

        }

        return false;
    }
}
