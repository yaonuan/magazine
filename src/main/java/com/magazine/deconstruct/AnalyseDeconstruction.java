package com.magazine.deconstruct;
//todo 关于标点符号,分词

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLSentence;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLWord;
import com.hankcs.hanlp.corpus.document.sentence.Sentence;
import com.hankcs.hanlp.corpus.document.sentence.word.Word;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AnalyseDeconstruction {
	private HashMap dict;

	public AnalyseDeconstruction() {
		this.dict = this.defineMap();


	}

	public HashMap defineMap() {
		HashMap dict = new HashMap();
		dict.put("主谓关系", "SBV");
		dict.put("动宾关系", "VOB");
		dict.put("间宾关系", "IOB");
		dict.put("前置宾语", "FOB");
		dict.put("兼语", "DBL");
		dict.put("定中关系", "ATT");
		dict.put("状中结构", "ADV");
		dict.put("动补结构", "CMP");
		dict.put("并列关系", "COO");
		dict.put("介宾关系", "POB");
		dict.put("左附加关系", "LAD");
		dict.put("右附加关系", "RAD");
		dict.put("独立结构", "IS");
		dict.put("核心关系", "HED");
		dict.put("标点符号", "WP");
		return dict;

	}


	public List<String> divideSentence(String sentences) {
		List<String> ret = new ArrayList<>();
		String sentence = "";
		String[] stopwords = {"。", "！", "？", "，", "\n", "；", "："};
		for (int i = 0; i < sentences.length(); i++) {
			sentence += sentences.charAt(i);
			if (Arrays.asList(stopwords).contains(String.valueOf(sentences.charAt(i)))) {
				if (sentence.length() > 0)
					ret.add(sentence.substring(0, sentence.length() - 1));
				sentence = "";
			}
		}
		if (sentence.length() > 0) {
			ret.add(sentence);
		}
		return ret;
	}


	public List<Object> build_parse_child_dict(String sentence) {
		List<Object> returnList = new ArrayList<>();
		int kernel = -1;
		MixData mixData = new MixData();
		List<HashMap> child_dict_list = new ArrayList<>();

		List<List<Object>> arcs = new ArrayList<>();

//        String s = "对自查发现的安全问题逐一做好记录";
		Sentence analyze = NLPTokenizer.analyze(sentence);
		List<Word> words_postags = analyze.toSimpleWordList();

		List<String> words = new ArrayList<>();
		List<String> postags = new ArrayList<>();
		for (Word w : words_postags) {
			words.add(w.value);
			postags.add(w.label);
		}


		CoNLLSentence parsedSentence = HanLP.parseDependency(sentence);
//        IDependencyParser parser = null;
//        try {
//            parser = new KBeamArcEagerDependencyParser();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        IDependencyParser parser = new NeuralNetworkDependencyParser().enableDeprelTranslator(false);

//        CoNLLSentence parsedSentence = parser.parse(sentence);

		for (CoNLLWord word : parsedSentence) {
			List<Object> tuple = new ArrayList<>();
			if (!word.DEPREL.equals("核心关系")) {
				if (words.contains(word.LEMMA) && words.contains(word.HEAD.LEMMA)) {
					tuple.add(word.ID - 1);
					tuple.add(this.dict.get(word.DEPREL));
					tuple.add(word.HEAD.ID - 1);
					arcs.add(tuple);
				}
			} else {
				kernel = words.indexOf(word.LEMMA);
			}
		}
//        System.out.println(arcs);

		for (int i = 0; i < words.size(); i++) {
			HashMap<Object, ArrayList<Integer>> dict_local = new HashMap();

			for (List<Object> arc : arcs) {

				if ((int) arc.get(2) == i) {
					if (!dict_local.containsKey(arc.get(1))) {
						ArrayList<Integer> list_local = new ArrayList<>();
						list_local.add((int) arc.get(0));
						dict_local.put(arc.get(1), list_local);
					} else {
						ArrayList<Integer> local = dict_local.get(arc.get(1));
						local.add((int) arc.get(0));
						dict_local.put(arc.get(1), local);
					}
				}

			}
			child_dict_list.add(dict_local);
		}
		mixData.setChild_dict(child_dict_list);
		mixData.setWords(words);
		mixData.setPostags(postags);
//		System.out.println("1\t"+mixData.getWords());
//		System.out.println("2\t"+mixData.getPostags());
//		System.out.println("3\t"+mixData.getChild_dict());

		returnList.add(mixData);
		returnList.add(kernel);
		return returnList;
	}

	/**
	 * 对宾语进行扩充
	 *
	 * @param mixData
	 * @param index
	 * @return
	 */
	public String completeElement(MixData mixData, int index) {
		HashMap<String, ArrayList<Integer>> child_dict = mixData.getChild_dict().get(index);
		String prefix = "";
		if (child_dict.containsKey("ATT")) {
			for (int i = 0; i < child_dict.get("ATT").size(); i++) {
				prefix += completeElement(mixData, child_dict.get("ATT").get(i));
			}
		}

		String postfix = "";

		//右附加关系
		if (child_dict.containsKey("RAD")) {
			postfix += completeElement(mixData, child_dict.get("RAD").get(0));
		}
		//'促进PPP项目各参与方诚实守信、严格履约' 因为有这个例外
		if (mixData.getPostags().get(index).equals("v") | mixData.getPostags().get(index).equals("vn") |
			(mixData.getPostags().get(index).equals("n") && mixData.getChild_dict().get(index).containsKey("VOB"))) {
			if (child_dict.containsKey("VOB")) {
				postfix += completeElement(mixData, child_dict.get("VOB").get(0));
			}
			if (child_dict.containsKey("SBV")) {
				prefix = completeElement(mixData, child_dict.get("SBV").get(0)) + prefix;
			}
			if (child_dict.containsKey("FOB")) {
				prefix = completeElement(mixData, child_dict.get("FOB").get(0)) + prefix;
			}
		}

		if (child_dict.containsKey("COO")) {
			for (int i = 0; i < child_dict.get("COO").size(); i++) {
				postfix += completeElement(mixData, child_dict.get("COO").get(i));
			}
		}
		return prefix + mixData.getWords().get(index) + postfix;
	}


	public String parseFirstSent(MixData mixData, String sentence) {
		if (mixData.getWords().size() <= 2) {
			return "";
		}
		if (!mixData.getPostags().get(0).equals("v")) {

			VerbObject<String, String> verbObject = new VerbObject<>();
			verbObject.setVerb(mixData.getWords().get(1));
			verbObject.setObject(mixData.subString(2, mixData.getWords().size()));
			return verbObject.toString();
		}

		VerbObject<String, String> verbObject = new VerbObject<>();
		verbObject.setVerb(mixData.getWords().get(0));
		verbObject.setObject(mixData.subString(1, mixData.getWords().size()));
		return verbObject.toString();

	}


	//针对既有ADV又有VOB的情况，可能V存在修饰的否定词，有进一步完善的空间
	public String expandVerb(MixData mixData, int firstIndex) {
		String[] forbidden = {"不得", "禁止", "严禁", "尚未"};
		if (mixData.getChild_dict().get(firstIndex).containsKey("ADV")) {
			String returnVerb = mixData.getWords().get(firstIndex);
			ArrayList<Integer> list = (ArrayList<Integer>) mixData.child_dict.get(firstIndex).get("ADV");
			for (int index : list) {
				if (!mixData.getPostags().get(index).contains("POB") && Arrays.asList(forbidden).contains(mixData.getWords().get(index))) {
					returnVerb = mixData.getWords().get(index) + returnVerb;
				}
			}

			return returnVerb;
		} else {
			return mixData.getWords().get(firstIndex);
		}
	}

	public int getIndex(MixData mixData, ArrayList<Integer> lists, String s) {
		int returnIndex = -1;
		for (int index : lists) {
			if (mixData.getChild_dict().get(index).containsKey(s)) {
				returnIndex = index;
				break;
			}
		}
		return returnIndex;
	}


	public String parseSingleSentence(String sentence) {
//        if(sentence.startsWith("为基层民政部门开展"))
//            System.out.println(111);

		Pattern p = Pattern.compile("^（[一二三四五六七八九]）");
		Matcher m = p.matcher(sentence);
		if (m.find()) {
			String sentence_local = sentence.substring(3, sentence.length()).trim();


			return this.parseFirstSent((MixData) this.build_parse_child_dict(sentence_local).get(0), sentence_local);
		}

		Pattern p2 = Pattern.compile("^[一二三四五六七八九]、");
		Matcher m2 = p2.matcher(sentence);
		if (m2.find()) {
			String sentence_local = sentence.substring(2, sentence.length()).trim();
			return this.parseFirstSent((MixData) this.build_parse_child_dict(sentence_local).get(0), sentence_local);
		}

		List<Object> listAndInteger = this.build_parse_child_dict(sentence);
		MixData mixData = (MixData) listAndInteger.get(0);
		// kernel 核心词
		int kernel = (int) listAndInteger.get(1);


//        可能会略显不足
		if (mixData.size() <= 21 && mixData.size() > 2) {
//            int firstIndex = this.getFirstIndex(mixData,start);
			int firstIndex = kernel;
			if (firstIndex < 0) {
				return "";
			}

			VerbObject<String, String> verbObject = new VerbObject<>();

			if (mixData.getPostags().get(firstIndex).equals("v") | (mixData.getPostags().get(firstIndex).equals("p") && mixData.getChild_dict().get(firstIndex).containsKey("VOB") && mixData.getChild_dict().get(firstIndex).containsKey("ADV"))) {
				if (mixData.getChild_dict().get(firstIndex).containsKey("VOB") && !mixData.getChild_dict().get(firstIndex).containsKey("ADV")) {
					ArrayList<Integer> local = (ArrayList<Integer>) mixData.getChild_dict().get(firstIndex).get("VOB");

//                    如果B在最后面
					if (local.get(0) == mixData.size() - 1 | local.get(0) == mixData.size() - 2) {
						verbObject.set(mixData.getWords().get(firstIndex), mixData.subString(firstIndex + 1, mixData.size()));
						return verbObject.toString();
					} else {
						// 宾语进行扩充
						String objectPhrase = completeElement(mixData, local.get(0));
						verbObject.set(mixData.getWords().get(firstIndex), objectPhrase);
						return verbObject.toString();
					}
				}
				//例句：现将部领导审定的《民政部2018年政务公开工作要点》印发你们
				else if (!mixData.getChild_dict().get(firstIndex).containsKey("VOB") && mixData.getChild_dict().get(firstIndex).containsKey("ADV")) {
					//可能存在多个ADV
					ArrayList<Integer> advList = (ArrayList<Integer>) mixData.getChild_dict().get(firstIndex).get("ADV");
					if (advList.size() == 1) {
						HashMap<String, ArrayList<Integer>> advContent = mixData.getChild_dict().get(advList.get(0));
						if (advContent.containsKey("POB")) {
							int objIndex = advContent.get("POB").get(0);
							//如果POB的宾语就位于动词的前面
							if (objIndex == firstIndex - 1) {
								verbObject.set(expandVerb(mixData, firstIndex), mixData.subString(advList.get(0) + 1, objIndex + 1));
								return verbObject.toString();
							} else {
								verbObject.set(expandVerb(mixData, firstIndex), completeElement(mixData, objIndex));
								return verbObject.toString();
							}
						} else {
							//尽快组织编写实施方案、规划示意图等相关材料
							if (mixData.getChild_dict().get(firstIndex).containsKey("COO")) {
								int cooIndex = firstIndex;
								do {
									ArrayList<Integer> cooList = (ArrayList<Integer>) mixData.getChild_dict().get(cooIndex).get("COO");
									if (cooList == null) {
										cooIndex = -1;
										break;
									}
									cooIndex = cooList.get(0);
									if (mixData.getChild_dict().get(cooIndex).containsKey("VOB"))
										break;
								} while (cooIndex < mixData.size());

								if (cooIndex < 0)
									return "";
								else {
									ArrayList<Integer> vobList = (ArrayList<Integer>) mixData.getChild_dict().get(cooIndex).get("VOB");
									verbObject.set(expandVerb(mixData, cooIndex), completeElement(mixData, vobList.get(0)));
									return verbObject.toString();
								}

							}
							return "";
						}
					} else {
						//可能存在特殊情况从而导致算法不够精准
						for (int index : advList) {
							if (!mixData.getChild_dict().get(index).containsKey("POB"))
								continue;
							else {
								ArrayList<Integer> pobList = (ArrayList<Integer>) mixData.getChild_dict().get(index).get("POB");
								verbObject.set(mixData.getWords().get(firstIndex), completeElement(mixData, pobList.get(0)));
								break;
							}
						}
						return verbObject.toString();
					}
				}
				//例句：对自查发现的安全问题逐一做好记录
				else if (mixData.getChild_dict().get(firstIndex).containsKey("VOB") && mixData.getChild_dict().get(firstIndex).containsKey("ADV")) {
					ArrayList<Integer> vobList = (ArrayList<Integer>) mixData.getChild_dict().get(firstIndex).get("VOB");
					ArrayList<Integer> advList = (ArrayList<Integer>) mixData.getChild_dict().get(firstIndex).get("ADV");
					//只做以下考虑可能会略显不足
					int advStartIndex = getIndex(mixData, advList, "POB");
					int vobStartIndex = vobList.get(vobList.size() - 1);
					//以介宾短语为主
					if (firstIndex - advStartIndex > vobStartIndex - firstIndex) {
						if (advStartIndex < 0)
							return "";
						ArrayList<Integer> pobList = (ArrayList<Integer>) mixData.getChild_dict().get(advStartIndex).get("POB");
						verbObject.set(mixData.subString(firstIndex, mixData.size()), completeElement(mixData, pobList.get(pobList.size() - 1)));
						return verbObject.toString();
					}
					//以动宾短语为主
					else {
						verbObject.set(expandVerb(mixData, firstIndex), completeElement(mixData, vobStartIndex));
						return verbObject.toString();
					}
				}
			}

			//单纯的介宾短语：
			// 1.以一般工商业用户的度电均价（电费/电量）为基准（为应该是动词）
			// 2.通过用户抽样调查、“解剖麻雀”、电网企业协助调查等方式
			else if (mixData.getPostags().get(firstIndex).equals("p")) {
				if (mixData.getChild_dict().get(firstIndex).containsKey("POB")) {
					ArrayList<Integer> pob_list = (ArrayList<Integer>) mixData.getChild_dict().get(firstIndex).get("POB");
					int obj_index = pob_list.get(pob_list.size() - 1);
//                    宾语在最后一句
					verbObject.set(mixData.getWords().get(firstIndex), mixData.subString(firstIndex + 1, mixData.size()));
					return verbObject.toString();
				}

			}

		}

		return "";
	}

	/**
	 * 解构
	 *
	 * @param sentences 需解构的字符串
	 * @return 解构完之后的字符组
	 */
	public ArrayList<String> paragraph_analyse(String sentences) {
		sentences = cleanText(sentences);
		String[] badSentences = {"相关意见", "以下意见", "有关部门", "本办法", "有关事项", "措施建议"};
		ArrayList<String> result_sentences = new ArrayList<>();
		List<String> sentences_split = this.divideSentence(sentences);
		for (String sentence : sentences_split) {
//            System.out.println(sentence);2222222
			String single_result = this.parseSingleSentence(sentence);
			if (single_result.length() > 0 && !single_result.contains("null")) {
				String verb = single_result.split(",")[0].substring(1, single_result.split(",")[0].length());
				String object = single_result.split(",")[1].substring(0, single_result.split(",")[1].length() - 1);
				if (verb.length() > 1 && object.length() > 2 && !Arrays.asList(badSentences).contains(object)) {
					result_sentences.add(single_result);
				}
			}
		}
		return result_sentences;

	}

	public String cleanText(String s) {
		String s1 = "</p>";
		Pattern p1 = Pattern.compile(s1);
		Matcher m1 = p1.matcher(s);
		String returnS = m1.replaceAll("。");


		String s2 = "(<table.*?</table>)|(<.*?>)|(&nbsp)|(\\u3000)|(\\s)";
		Pattern p2 = Pattern.compile(s2);
		Matcher m2 = p2.matcher(returnS);
		returnS = m2.replaceAll("");

		return returnS;

	}


}
