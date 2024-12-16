package org.js.checkoutcomponent.api;

import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.js.checkoutcomponent.model.ItemPrice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("checkout-component")
public class CheckoutComponentController {

    @GetMapping("/items")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success - Item returned", content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ItemPrice.class))},
                    headers = {@Header(name = "X-REQUEST-ID", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "404", description = "Item not found", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema =
            @Schema(implementation = ItemPrice.class))},
                    headers = {@Header(name = "X-REQUEST-ID", schema = @Schema(implementation = String.class))}),

    })
    public ResponseEntity<ItemPrice> getItem() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
