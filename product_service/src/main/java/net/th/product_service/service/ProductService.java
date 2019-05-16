package net.th.product_service.service;

import net.th.product_service.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> listProduct();

    Product findById(int id);


}
