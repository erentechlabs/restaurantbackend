package com.restaurantbackend.restaurantbackend.order;

import com.restaurantbackend.restaurantbackend.menu.MenuRepository;
import com.restaurantbackend.restaurantbackend.menu.MenuItem;
import com.restaurantbackend.restaurantbackend.session.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;

    @Transactional
    public OrderDTO placeOrder(Session session, List<OrderItemDTO> itemsDto) {
        final Order order = new Order();  // final yaptık
        order.setSession(session);
        order.setOrderTime(LocalDateTime.now());

        List<OrderItem> items = itemsDto.stream().map(dto -> {
            MenuItem menuItem = menuRepository.findByName(dto.getMenuItemName())
                    .orElseThrow(() -> new IllegalArgumentException("Menu item not found: " + dto.getMenuItemName()));
            OrderItem item = new OrderItem();
            item.setMenuItem(menuItem);
            item.setQuantity(dto.getQuantity());
            item.setOrder(order);  // artık sorun yok
            return item;
        }).toList();

        order.setItems(items);
        orderRepository.save(order);

        // DTO dönüşü
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setOrderTime(order.getOrderTime());
        dto.setItems(itemsDto); // veya dönüştür
        return dto;
    }

}
