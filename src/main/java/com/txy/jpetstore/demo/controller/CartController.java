package com.txy.jpetstore.demo.controller;

import com.txy.jpetstore.demo.domain.CartItem;
import com.txy.jpetstore.demo.domain.CartItemVO;
import com.txy.jpetstore.demo.domain.Item;
import com.txy.jpetstore.demo.domain.Order;
import com.txy.jpetstore.demo.service.CartService;
import com.txy.jpetstore.demo.service.ItemService;
import com.txy.jpetstore.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@CrossOrigin
@RequestMapping("/cart")
@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;
    @Autowired
    private ItemService itemService;

    @GetMapping("/{username}")
    public Map<String, Object> viewCart(@PathVariable("username") String username) {
        Item item ;
        BigDecimal totalCount=new BigDecimal(0);
        List<CartItemVO> cartItems = new ArrayList<CartItemVO>();
        for (CartItem cartItem : cartService.viewCart(username)) {
            item=itemService.searchOne(cartItem.getItemid());
            cartItems.add(new CartItemVO(item,cartItem));
            totalCount=totalCount.add(cartItem.getTotalcost());
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 200);
        map.put("message", "ok");
        map.put("totalCount", totalCount);
        map.put("itemInCart", cartItems);
        return map;
    }

    @DeleteMapping("/{username}")
    public Map<String, Object> delete(@RequestBody CartItem cartItem) {
        Map<String, Object> map = new HashMap<String, Object>();
        int i = cartService.deleteItem(cartItem);
        BigDecimal totalCount=new BigDecimal(0);
        for (CartItem cart : cartService.viewCart(cartItem.getUsername())) {
            totalCount=totalCount.add(cart.getTotalcost());
        }
        if ( i== 1) {
            map.put("code", 200);
            map.put("message", "ok");
            map.put("totalCount",totalCount);
        } else {
            map.put("code", 501);
            map.put("message", "no");
            map.put("wrongmessage", cartItem.getItemid() + " deletes failed");
        }
        return map;
    }

    @PutMapping("/{username}")
    public Map<String, Object> update(@RequestBody CartItem cartItem) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (cartService.updateItem(cartItem) == 1) {
            map.put("code", 200);
            map.put("message", "ok");
        }else {
            map.put("code", 501);
            map.put("message", "no");
            map.put("wrongmessage", cartItem.getItemid() + " updates failed");
        }
        return map;
    }

    @Transactional
    @PostMapping("/{username}")
    public Map<String, Object> add(@RequestBody CartItem cartItem,@PathVariable("username")String username) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.addAll(cartService.viewCart(username));
        boolean flag=false;
        if (cartItems.size()!=0){
            for (CartItem item : cartItems) {
                if (Objects.equals( cartItem.getItemid(), item.getItemid())){
                    CartItem newCartItem=item;
                    newCartItem.setInstock(item.getInstock()+1);
                    newCartItem.setTotalcost(newCartItem.getListprice().multiply(new BigDecimal(newCartItem.getInstock())));
                    cartService.updateItem(newCartItem);
                    flag=true;
                    map.put("code",201);
                    map.put("message", "ok");
                    break;
                }
            }
        }
        if (!flag) {
            if (cartService.addItem(cartItem) == 1) {
                map.put("code", 200);
                map.put("message", "ok");
            }else {
                map.put("code", 501);
                map.put("message", "no");
                map.put("wrongmessage", cartItem.getItemid() + " adds failed");
            }
        }

        return map;
    }


    @DeleteMapping("/clear/{username}")
    public Map<String, Object> clear(@PathVariable("username") String username) {
        Map<String, Object> map = new HashMap<String, Object>();
        cartService.clearCart(username);
        map.put("code",200);
        map.put("message","ok");
        return map;
    }

}
