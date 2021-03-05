package com.example.service;

import com.example.ProductCategory;
import com.example.model.Product;
import com.example.model.User;
import com.example.util.Valid;

import java.util.*;
import java.util.stream.Collectors;

public class BackToStockServiceImpl implements BackToStockService{
    private final Map<Integer,List<User>> products;

    public BackToStockServiceImpl( Map<Integer,List<User>> products){
        this.products = products;
    }

    @Override
    public void subscribe(User user, Product product) {
        Valid.checkNotFound(user,"user");
        Valid.checkNotFound(product,"product");
        List<User> users =  Valid.checkNotFound(products.get(product.getId()),"list of users");
        user.setId(users.size() + 1);
        users.add(user);
        products.put(product.getId(), users);
    }

    @Override
    public List<User> subscribedUsers(Product product) {
        Valid.checkNotFound(product,"product");
        Valid.checkNotFound(products.get(product.getId()),"list of users");
        if(product.getCategory() == ProductCategory.MEDICAL){
            return products.get(product.getId()).stream()
                    .peek(user ->{
                        if (user.getAge() > 70) {
                            user.setPremium(true);
                        }
                    })
                    .sorted(Comparator.comparing(User::isPremium).reversed())
                    .collect(Collectors.toList());
        }
            return products.get(product.getId()) == null? null : products.get(product.getId()).stream()
                .sorted(Comparator.comparing(User::isPremium)
                .thenComparing(User::isElderThen70).reversed())
                .collect(Collectors.toList());
    }
}
