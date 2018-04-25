package com.anke.yingxiang.store;

import com.anke.yingxiang.util.AjaxResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qingxiangzhang on 2017/11/22.
 */
@RestController
@RequestMapping("store")
public class StoreController {

    private AjaxResponseUtil ajaxResponseUtil;

    @RequestMapping(value = ("/products"), method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    @Transactional
    public List<Product> allProducts() {
        List<Product> products = new ArrayList<>();
        Manufacturer manufacturer = new Manufacturer("XX机电");
        for(int i=0; i<10; i++){
            Product product = new Product(i, "产品"+i, "http://ojqwtoohq.bkt.clouddn.com/19186_26.jpg", manufacturer, "￥"+(int) (Math.random()*1000));
            products.add(product);
        }

        return products;
    }

    @RequestMapping(value = ("/products/{id}"), method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    @Transactional
    public Product getProduct(@PathVariable Integer id) {
        Manufacturer manufacturer = new Manufacturer("XX机电");
        Product product = new Product(id, "产品"+id, "http://ojqwtoohq.bkt.clouddn.com/19186_26.jpg", manufacturer, "￥"+(int) (Math.random()*1000));
        product.setDescription("Bootstrap’s form controls expand on our Rebooted form styles with classes. Use these classes to opt into their customized displays for a more consistent rendering across browsers and devices.\n" +
                "\n" +
                "Be sure to use an appropriate type attribute on all inputs (e.g., email for email address or number for numerical information) to take advantage of newer input controls like email verification, number selection, and more.\n" +
                "\n" +
                "Here’s a quick example to demonstrate Bootstrap’s form styles. Keep reading for documentation on required classes, form layout, and more.");
        return product;
    }


}
