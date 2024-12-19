package org.js.checkoutcomponent.service.item.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.js.checkoutcomponent.service.item.entities.BundleDiscountEntity;
import org.js.checkoutcomponent.service.item.entities.ItemDiscountEntity;
import org.js.checkoutcomponent.service.item.entities.ItemEntity;

import java.util.List;

@Mapper
public interface ItemsMapper {

    @Select("SELECT * FROM item")
    @Results({ @Result(property = "id", column = "id", javaType = String.class, jdbcType = JdbcType.VARCHAR),//
        @Result(property = "name", column = "name", javaType = String.class, jdbcType = JdbcType.VARCHAR),//
        @Result(property = "normalPrice", column = "normal_price", javaType = java.math.BigDecimal.class, jdbcType = JdbcType.DECIMAL),//
        @Result(property = "createDate", column = "create_date", javaType = java.time.LocalDate.class, jdbcType = JdbcType.DATE),//
        @Result(property = "updateDate", column = "update_date", javaType = java.time.LocalDate.class, jdbcType = JdbcType.DATE)//
    })
    List<ItemEntity> fetchAllItems();

    @Select("SELECT * FROM item_discount")
    @Results({ @Result(property = "id", column = "id", javaType = String.class, jdbcType = JdbcType.VARCHAR),//
        @Result(property = "itemId", column = "item_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),//
        @Result(property = "quantity", column = "quantity, javaType = int.class, jdbcType = JdbcType.INTEGER"),//
        @Result(property = "specialPrice", column = "special_price", javaType = java.math.BigDecimal.class, jdbcType = JdbcType.DECIMAL),//
        @Result(property = "validFrom", column = "valid_from", javaType = java.time.LocalDate.class, jdbcType = JdbcType.DATE),//
        @Result(property = "validTo", column = "valid_to", javaType = java.time.LocalDate.class, jdbcType = JdbcType.DATE)//
    })
    List<ItemDiscountEntity> fetchAllDiscounts();

    @Select("SELECT * FROM bundle_discount")
    @Results({ @Result(property = "id", column = "id", javaType = String.class, jdbcType = JdbcType.VARCHAR),//
        @Result(property = "fromItemId", column = "from_item_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),//
        @Result(property = "toItemId", column = "to_item_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),//
        @Result(property = "discountPrice", column = "discount_price", javaType = java.math.BigDecimal.class, jdbcType = JdbcType.DECIMAL),//
        @Result(property = "validFrom", column = "valid_from, javaType = java.time.LocalDate.class, jdbcType = JdbcType.DATE"),//
        @Result(property = "validTo", column = "valid_to, javaType = java.time.LocalDate.class, jdbcType = JdbcType.DATE")//
    })
    List<BundleDiscountEntity> fetchAllBundleDiscounts();

}



