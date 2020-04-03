package com.how2java.tmall.controller;

import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.PropertyValue;
import com.how2java.tmall.service.ProductService;
import com.how2java.tmall.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("")
public class PropertyValueController {

    @Autowired
    PropertyValueService propertyValueService;

    @Autowired
    ProductService productService;
    @RequestMapping("admin_propertyValue_edit")
    public String edit(Model model,int pid){
        //根据传过来的产品id获取相应的产品
        Product p=productService.get(pid);
        /**
         * init是通过产品定位category 然后定位category对应的所有property
         *获得的是一个分类下所有的产品的所有属性值
         * 需要根据产品id确定那些是这个产品的，然后根据属性id与属性值对应
         * 没有的话要进行创建
         */
        propertyValueService.init(p);
        /**
         * propertyvalueservice的List
         * 是根据产品的id获得List<PropertyValue>即对应的所有propertyvalue
         *  Property property=propertyService.get(pv.getPtid());
         *   pv.setProperty(property);
         *  使得value和property对应起来
         */
        List<PropertyValue> pvs=propertyValueService.list(p.getId());
        model.addAttribute("p",p);
        model.addAttribute("pvs",pvs);
        return "admin/editPropertyValue";

    }
    @RequestMapping("admin_propertyValue_update")
    @ResponseBody
    public String update(PropertyValue pv){
        propertyValueService.update(pv);
        return "success";
    }


}
