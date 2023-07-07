package org.senyk.bookmap.v1;

import java.util.*;

public class ComponentService {
    private Map<Integer,Integer> bid=new HashMap<>();
    private Map<Integer,Integer> ask=new HashMap<>();
    public static List<String> output(List<String> input){
        List<String> result=new ArrayList<>();
        ComponentService service=new ComponentService();
        for (String line: input){
            String[] words = line.split(",");
            if(words[0].equalsIgnoreCase("u")){
                service.update(words);
            } else if(words[0].equalsIgnoreCase("q")){
                result.add(service.query(words));
            } else if (words[0].equalsIgnoreCase("o")) {
                service.order(words);
            }
        }
        return result;
    }

    private String query(String[] words){
        if (words[1].equalsIgnoreCase("best_ask")){
            return queryAsk();
        } else if (words[1].equalsIgnoreCase("best_bid")){
            return queryBid();
        }else {
            return querySize(Integer.parseInt(words[2]));
        }
    }
    private String queryAsk(){
        List<Integer> listOfKeys= new ArrayList<>(ask.keySet());
        String s;
        for (int i = 0; i <ask.size(); i++) {
            int k= listOfKeys.get(i);
            int v= ask.get(k);
            if (v == 0)
                continue;
            s=k+","+v;
            return s;
        }
        return "";
    }
    private String queryBid(){
        List<Integer> listOfKeys= new ArrayList<>(bid.keySet());
        String s;
        for (int i = bid.size()-1; i >=0; i--) {
            int k= listOfKeys.get(i);
            int v= bid.get(k);
            if (v == 0)
                continue;
            s=k+","+v;
            return s;
        }
        return "";
    }
    private String querySize(Integer price){
        if(!(price>=Orders.SPREAD.MIN_SIZE&&price<=Orders.SPREAD.MAX_SIZE))return "";
        if(bid.containsKey(price)){
            if (bid.get(price)==0 && ask.containsKey(price))
                return String.valueOf(ask.get(price));
            return String.valueOf(bid.get(price));
        }
        else if(ask.containsKey(price)){
            return String.valueOf(ask.get(price));
        }
        else {
            return "0";
        }
    }
    private void update(String[] words){
        int price=Integer.parseInt(words[1]);
        int size=Integer.parseInt(words[2]);
        if (price>=Orders.SPREAD.MIN_PRICE&&
            price<=Orders.SPREAD.MAX_PRICE&&
            size>=Orders.SPREAD.MIN_SIZE&&
            size<=Orders.SPREAD.MAX_SIZE){
            if (words[3].equals(Orders.BID.getName()))
                bid.put(price,size);
            else if (words[3].equals(Orders.ASK.getName()))
                ask.put(price,size);
        }
    }

    private void order(String[] words){
        int x;
        try {
            x=Integer.parseInt(words[2]);
            if (x<0)throw new Exception();
        }catch (Exception e){return;}
        if (words[1].equalsIgnoreCase("buy")){
            orderBuy(x);
        } else {
            orderSell(x);
        }
    }
    private void orderBuy(Integer size){
        List<Map.Entry<Integer, Integer> > entryList = new ArrayList<>(ask.entrySet());
        for (int i = 0; i < ask.size(); i++) {
            int v=entryList.get(i).getValue();
            int k=entryList.get(i).getKey();
            if (v == 0)
                continue;
            if (v >size){
                ask.put(k,v-size);
                break;
            } else if(v==size){
                ask.put(k,0);
                break;
            }else {
                size-=v;
                ask.put(k,0);
            }
        }
        entryList.clear();
    }
    private void orderSell(Integer size){
        List<Map.Entry<Integer, Integer> > entryList = new ArrayList<>(bid.entrySet());
        for (int i = bid.size()-1; i >=0; i--) {
            int v=entryList.get(i).getValue();
            int k=entryList.get(i).getKey();
            if (v ==0)
                continue;
            if (v >size){
                bid.put(k,v-size);
                break;
            } else if(v==size){
                bid.put(k,0);
                break;
            }
            else {
                size-=v;
                bid.put(k,0);
            }
        }
        entryList.clear();
    }
}
