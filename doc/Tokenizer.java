package com.zq.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author zhangqi
 * @description 中文分词器
 * @date 2020/1/14 9:53
 */
public class Tokenizer {
    private Map<Character, Object> dictionary;

    public Tokenizer(String dictionaryFilePath) throws IOException {
        dictionary = new TreeMap<>();   //红黑树的实现
        //从文件加载字典到treeMap
        this.loadDictionary(dictionaryFilePath);
    }

    private void loadDictionary(String dictionaryFilePath) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(dictionaryFilePath)));
        String line = null;
        //循环读取下一行
        while((line = reader.readLine()) != null){
            line = line.trim();
            if(line.length() == 0){
                continue;
            }
            char c;
            Map<Character, Object> child = this.dictionary;

            //组成这个字符开头的词的树
            for (int i = 0; i < line.length(); i++) {
                c = line.charAt(i);
                Map<Character, Object> ccMap = (Map<Character, Object>) child.get(c);
                if(ccMap == null){
                    ccMap = new HashMap<Character, Object>();
                    child.put(c,ccMap);
                }
                child = ccMap;
            }
            child.put(' ',null); //词结束符，用以区分 例：程序、程序员 他们都是词
        }
    }

    public List<String> participle(String text){
        if(text == null){
            return null;
        }
        text = text.trim();
        if(text.length() == 0){
            return null;
        }

        List<String> tokens = new ArrayList<>();
        char c;
        for (int i = 0; i < text.length();) {
            StringBuilder token = new StringBuilder();
            Map<Character, Object> child = this.dictionary;
            boolean matchToken = false;
            for (int j = i; j < text.length(); j++) {
                c = text.charAt(j);
                Map<Character, Object> ccMap = (Map<Character, Object>) child.get(c);
                if(ccMap == null){
                    if(child.containsKey(' ')){
                        matchToken = true;
                        i = j;
                    }
                    break;
                }else{
                    token.append(c);
                    child = ccMap;
                }
            }

            if(matchToken){ //匹配到词
                tokens.add(token.toString());
            }else{
                if(child.containsKey(' ')){
                    tokens.add(token.toString());
                    break;
                }else{  //没有匹配到词，则该字符单独作为一个词
                    tokens.add("" + text.charAt(i));
                    i++;
                }
            }
        }
        return tokens;
    }

    public static void main(String[] args) throws IOException{
        Tokenizer tk = new Tokenizer(Tokenizer.class.getResource("/dictionary.txt").getPath());
        List<String> tokens = tk.participle("夏天能穿多少穿多少，冬天能穿多少穿多少");
        for(String s: tokens){
            System.out.println(s);
        }
    }
}
