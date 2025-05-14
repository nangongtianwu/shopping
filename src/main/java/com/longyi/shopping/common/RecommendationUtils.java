package com.longyi.shopping.common;

import com.longyi.shopping.entity.*;

import java.util.*;
import java.util.stream.Collectors;

public class RecommendationUtils {
//    随机推荐
    public static  List<Goods> random(List<Goods> list, int num){
        if(list==null||list.size()==0){
            return null;
        }
        if (num<=0){
            return null;
        }
        if (num>list.size()){
            num = list.size();
        }
        List<Goods> goods = new ArrayList<>(list);
        Collections.shuffle(goods);
       return goods.subList(0,num);
    }
    public static List<Goods> UBCF(List<Goods> list, List<Collect> collectList, List<Comment> commentList, List<Look> lookList, List<Shopping> shoppingList){
        Map<Integer,Integer> ubcf = new HashMap<Integer,Integer>();
        for (Collect collect:collectList) {
            ubcf.put(collect.getRelation(),ubcf.getOrDefault(collect.getRelation(),0)+3);
            System.out.println(ubcf);
        }
        for (Comment comment:commentList) {
            ubcf.put(comment.getRelation(),ubcf.getOrDefault(comment.getRelation(),0)+comment.getRating());
        }
        System.out.println(ubcf);
        for (Look look:lookList) {
            ubcf.put(look.getGoods(),ubcf.getOrDefault(look.getGoods(),0)+1);
        }
        System.out.println(ubcf);
        for (Shopping shopping:shoppingList) {
            ubcf.put(shopping.getGoods(),ubcf.getOrDefault(shopping.getGoods(),0)+shopping.getCount());

        }
        System.out.println(ubcf);
        PriorityQueue<Map.Entry<Integer,Integer>> pq=new PriorityQueue<>((a,b)->b.getValue().compareTo(a.getValue()));
        pq.addAll(ubcf.entrySet());
        List<Goods> goods=new ArrayList<>();
        for (int i=0;i<8&&!pq.isEmpty();i++){
            Map.Entry<Integer,Integer> entry=pq.poll();
            int goodsId=entry.getKey();
            Goods good=list.stream().filter(g->g.getId()==goodsId).findFirst().orElse(null);
            if (good!=null){
                goods.add(good);
            }
        }
        if(goods.size()<8){
            System.out.println(goods);
            System.out.println(list);
            System.out.println(deduplicate(list,goods));
            goods=random(deduplicate(list,goods),8-goods.size());

        }
        return goods;
    }
    public static List<Goods> IBCF(List<Goods> list, Goods goods) {
        List<Goods> goodsList = list.stream().filter(item -> !item.getId().equals(goods.getId())).collect(Collectors.toList());
        if(goodsList.isEmpty()){
            return null;
        }
        Map<Goods,Double> goodsScore=new HashMap<>();
        for (Goods item:goodsList){
            double score=0.0;
            double typeScore=(item.getType().equals(goods.getType())?2:1);
            double collectScore=item.getCollect();
            double clickScore=item.getClick();
            double goodScore=item.getGood()*4+item.getComment();
            double saleScore=item.getSale()*item.getPrice();
            score=typeScore+clickScore+collectScore*3+goodScore+saleScore;
            goodsScore.put(item,score);

        }
        List<Map.Entry<Goods, Double>> sortGoods = goodsScore.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toList());
        List<Goods> recommendGoods= sortGoods.stream().limit(4).map(Map.Entry::getKey).collect(Collectors.toList());
        if(recommendGoods.size()<4){
            recommendGoods=random(deduplicate(goodsList,recommendGoods),4-recommendGoods.size());
        }
        return recommendGoods;
    }
    private static List<Goods> deduplicate(List<Goods> list, List<Goods> goods) {
        Set<Goods> set=new HashSet<>();
        for (Goods good:list) {
            set.add(good);
        }
        for (Goods good:goods) {
            set.remove(good);
        }
        List<Goods> goodsList=new ArrayList<>(set);
        return goodsList;
    }
}
