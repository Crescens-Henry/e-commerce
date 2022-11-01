package pasteleria.ecommerce.entities.projections;

public interface ProductProjection {
    Long getId();

    String getName();

    double getPrice();

    String getCode();

    String getDescription();

    Integer getQuantity();

    String getOrderDate();
}
