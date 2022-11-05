package com.escuelita.demo.repositories;

import java.util.List;

import com.escuelita.demo.entities.pivots.ProductOrder;
import com.escuelita.demo.entities.projections.OrderProjection;
import com.escuelita.demo.entities.projections.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductOrderRepository extends JpaRepository<ProductOrder, Long> {
        @Query(value = "select products.* from products_orders " +
                        "inner join products on products_orders.product_id = products.id " +
                        "inner join orders on products_orders.order_id = orders.id " +
                        "where products_orders.order_id = :orderId", nativeQuery = true)
        List<ProductProjection> listAllProductsByOrderId(@Param("orderId") Long orderId);

        @Query(value = "select orders.*, products.name as productName from products_orders " +
                        "inner join orders on products_orders.order_id = orders.id " +
                        "inner join products on products_orders.product_id = products.id " +
                        "where products_orders.product_id = :productId", nativeQuery = true)
        List<OrderProjection> listAllOrdersByProductId(@Param("productId") Long productId);
}
