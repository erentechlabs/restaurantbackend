package com.restaurantbackend.restaurantbackend.order;

import com.restaurantbackend.restaurantbackend.menu.MenuItem;
import com.restaurantbackend.restaurantbackend.menu.MenuRepository;
import com.restaurantbackend.restaurantbackend.session.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final MenuRepository menuRepository;

    public Order toEntity(Session session, List<OrderItemDTO> itemsDto) {
        Order order = new Order();
        order.setSession(session);
        order.setOrderTime(java.time.LocalDateTime.now());

        List<OrderItem> items = itemsDto.stream().map(dto -> {
            MenuItem menuItem = menuRepository.findByName(dto.getMenuItemName())
                    .orElseThrow(() -> new IllegalArgumentException("Menu item not found: " + dto.getMenuItemName()));
            OrderItem item = new OrderItem();
            item.setMenuItem(menuItem);
            item.setQuantity(dto.getQuantity());
            item.setOrder(order);
            return item;
        }).collect(Collectors.toList());

        order.setItems(items);
        return order;
    }

    public OrderDTO toDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setOrderTime(order.getOrderTime());
        dto.setItems(order.getItems().stream().map(item -> {
            OrderItemDTO itemDTO = new OrderItemDTO();
            itemDTO.setMenuItemName(item.getMenuItem().getName());
            itemDTO.setQuantity(item.getQuantity());
            return itemDTO;
        }).collect(Collectors.toList()));
        return dto;
    }
}
