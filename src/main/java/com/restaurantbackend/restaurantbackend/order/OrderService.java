package com.restaurantbackend.restaurantbackend.order;

import com.restaurantbackend.restaurantbackend.menu.MenuItem;
import com.restaurantbackend.restaurantbackend.menu.MenuRepository;
import com.restaurantbackend.restaurantbackend.session.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;

    @Transactional
    public OrderDTO placeOrder(Session session, List<OrderItemDTO> itemsDto) {
        final Order order = new Order();
        order.setSession(session);
        order.setOrderTime(LocalDateTime.now());

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
        orderRepository.save(order);

        return convertOrderToDTO(order);
    }

    @Transactional
    public OrderDTO updateOrderItem(Long orderId, String oldItemName, OrderItemDTO newItemDTO) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        OrderItem item = order.getItems().stream()
                .filter(i -> i.getMenuItem().getName().equals(oldItemName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Order item not found"));

        MenuItem newMenuItem = menuRepository.findByName(newItemDTO.getMenuItemName())
                .orElseThrow(() -> new IllegalArgumentException("Menu item not found"));

        item.setMenuItem(newMenuItem);
        item.setQuantity(newItemDTO.getQuantity());

        return convertOrderToDTO(order);
    }

    private OrderDTO convertOrderToDTO(Order order) {
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
