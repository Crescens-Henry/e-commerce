package pasteleria.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pasteleria.ecommerce.entities.pivots.ProductOrder;
import pasteleria.ecommerce.entities.projections.OrderProjection;
import pasteleria.ecommerce.entities.projections.ProductProjection;

public interface IProductOrderRepository extends JpaRepository<ProductOrder, Long> {
    @Query(value = "select products.* from products_orders" +
            "inner join products on products_orders.product_id = products.id" +
            "inner join orders on products_orders.order_id = orders.id" +
            "where products_orders.orders_id = :orderId", nativeQuery = true)
    List<ProductProjection> listAllProductsByOrderId(Long orderId);

    @Query(value = "select orders.*, products.name as productName from products_orders" +
            "inner join products on products_orders.product_id = products.id" +
            "inner join orders on products_orders.order_id = orders.id" +
            "where products_orders.products_id = : productId", nativeQuery = true)
    List<OrderProjection> listAllOrdersByProductId(Long productId);
}
