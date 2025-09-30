// Yeni Service: OrderService (Siparişler için ayrı)
package com.restaurantbackend.restaurantbackend.service.order;

import com.restaurantbackend.restaurantbackend.entity.order.RestaurantOrder;
import com.restaurantbackend.restaurantbackend.entity.menu.MenuItem;
import com.restaurantbackend.restaurantbackend.dto.order.RestaurantOrderDTO;
import com.restaurantbackend.restaurantbackend.dto.order.AddOrderDTO;
import com.restaurantbackend.restaurantbackend.entity.session.TableSession;
import com.restaurantbackend.restaurantbackend.mapper.order.RestaurantOrderMapper;
import com.restaurantbackend.restaurantbackend.repository.order.RestaurantOrderRepository;
import com.restaurantbackend.restaurantbackend.repository.menu.MenuItemRepository;
import com.restaurantbackend.restaurantbackend.repository.table.TableSessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class OrderService {


    private final RestaurantOrderRepository orderRepository;

    private final TableSessionRepository sessionRepository;

    private final MenuItemRepository menuItemRepository;

    private final RestaurantOrderMapper orderMapper;

    public OrderService(RestaurantOrderRepository orderRepository, TableSessionRepository sessionRepository, MenuItemRepository menuItemRepository, RestaurantOrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.sessionRepository = sessionRepository;
        this.menuItemRepository = menuItemRepository;
        this.orderMapper = orderMapper;
    }

    @Transactional
    public RestaurantOrderDTO addOrderToSession(Long sessionId, AddOrderDTO dto) {

        if (dto.getMenuItemId() == null || dto.getQuantity() <= 0) {
            throw new IllegalArgumentException("MenuItem ID ve quantity zorunlu ve quantity > 0 olmalı");
        }

        TableSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Aktif oturum bulunamadı: " + sessionId));

        if (!session.isActive()) {
            throw new RuntimeException("Oturum aktif değil: " + sessionId);
        }

        MenuItem menuItem = menuItemRepository.findById(dto.getMenuItemId())
                .orElseThrow(() -> new RuntimeException("MenuItem bulunamadı: " + dto.getMenuItemId()));

        if (!menuItem.isAvailable()) {
            throw new RuntimeException("MenuItem mevcut değil: " + menuItem.getName());
        }

        RestaurantOrder order = orderMapper.toEntity(new RestaurantOrderDTO(), menuItem, session);
        order.setQuantity(dto.getQuantity());
        order.setTotalPrice(dto.getQuantity() * menuItem.getPrice());
        order = orderRepository.save(order);

        return orderMapper.toDTO(order);
    }

    public List<RestaurantOrderDTO> getOrdersForSession(Long sessionId) {
        TableSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Oturum bulunamadı"));
        return orderMapper.toDTOList(session.getOrders());
    }

    @Transactional
    public void deleteOrder(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new RuntimeException("Sipariş bulunamadı");
        }
        orderRepository.deleteById(orderId);
    }
}