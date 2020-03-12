package com.price.calculator.service.demand;

import com.price.calculator.model.Demand;
import com.price.calculator.model.Product;

import java.util.Collection;
import java.util.List;

import static com.price.calculator.model.Demand.demand;
import static java.util.stream.Collectors.*;

public class DemandManager {
    public Collection<Demand> extractDemand(List<Product> productList) {
        return productList.stream()
                .collect(groupingBy(product -> product, counting()))
                .entrySet()
                .stream()
                .map(entry -> demand(entry.getKey(), entry.getValue()))
                .collect(toSet());
    }
}
