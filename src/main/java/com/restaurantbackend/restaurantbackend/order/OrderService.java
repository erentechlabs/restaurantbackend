package com.restaurantbackend.restaurantbackend.order;

import com.restaurantbackend.restaurantbackend.menu.MenuItem;
import com.restaurantbackend.restaurantbackend.menu.MenuRepository;
import com.restaurantbackend.restaurantbackend.session.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;
    private final OrderMapper orderMapper;

    @Transactional
    public OrderDTO placeOrder(Session session, List<OrderItemDTO> itemsDto) {
        Order order = orderMapper.toEntity(session, itemsDto);
        orderRepository.save(order);
        return orderMapper.toDTO(order);
    }

    @Transactional
    public OrderDTO updateOrder(Session session, List<OrderItemDTO> newItems) {
        Order order = orderRepository.findBySession(session)
                .orElseThrow(() -> new IllegalArgumentException("No order found for this session"));

        for (OrderItemDTO newItemDTO : newItems) {
            OrderItem existingItem = order.getItems().stream()
                    .filter(i -> i.getMenuItem().getName().equals(newItemDTO.getMenuItemName()))
                    .findFirst()
                    .orElse(null);

            MenuItem menuItem = menuRepository.findByName(newItemDTO.getMenuItemName())
                    .orElseThrow(() -> new IllegalArgumentException("Menu item not found"));

            if (existingItem != null) {
                existingItem.setQuantity(existingItem.getQuantity() + newItemDTO.getQuantity());
            } else {
                OrderItem orderItem = new OrderItem();
                orderItem.setMenuItem(menuItem);
                orderItem.setQuantity(newItemDTO.getQuantity());
                orderItem.setOrder(order);
                order.getItems().add(orderItem);
            }
        }

        orderRepository.save(order);
        return orderMapper.toDTO(order);
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

        return orderMapper.toDTO(order);
    }
}
