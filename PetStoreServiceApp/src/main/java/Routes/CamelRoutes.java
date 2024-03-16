package Routes;

import Models.Product;
import Services.ProductService;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CamelRoutes extends RouteBuilder {

    @Autowired
    private ProductService productService;

    @Override
    public void configure() throws Exception {
        // Configurar ruta para obtener todos los productos
        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.json);

        rest("/api/products")
                .get()
                .outType(Product.class)
                .to("direct:getAllProducts");

        from("direct:getAllProducts")
                .bean(productService, "getAllProducts")
                .choice()
                .when(body().isNull())
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(404))
                .otherwise()
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200));

        // Configurar ruta para obtener un producto por ID
        rest("/api/products/{id}")
                .get()
                .outType(Product.class)
                .to("direct:getProductById");

        from("direct:getProductById")
                .setProperty("productId", simple("${header.id}"))
                .bean(productService, "getProductById(${header.id})")
                .choice()
                .when(body().isNull())
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(404))
                .otherwise()
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200));

        // Configurar ruta para crear un producto
        rest("/api/products")
                .post()
                .type(Product.class)
                .outType(Product.class)
                .to("direct:createProduct");

        from("direct:createProduct")
                .bean(productService, "createProduct")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201));

        // Configurar ruta para actualizar un producto
        rest("/api/products/{id}")
                .put()
                .type(Product.class)
                .to("direct:updateProduct");

        from("direct:updateProduct")
                .bean(productService, "updateProduct(${header.id}, ${body})")
                .choice()
                .when(body().isNull())
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(404))
                .otherwise()
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200));

        // Configurar ruta para eliminar un producto
        rest("/api/products/{id}")
                .delete()
                .to("direct:deleteProduct");

        from("direct:deleteProduct")
                .bean(productService, "deleteProduct(${header.id})")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(204));
    }
}
