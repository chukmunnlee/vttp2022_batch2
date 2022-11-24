package vttp.paf.day24.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vttp.paf.day24.models.PurchaseOrder;
import vttp.paf.day24.repositories.LineItemRepository;
import vttp.paf.day24.repositories.PurchaseOrderRepository;

@Service
public class OrderService {
    
    @Autowired
    private PurchaseOrderRepository poRepo;

    @Autowired
    private LineItemRepository liRepo;

    @Transactional(rollbackFor = OrderException.class)
    public void createNewOrder(PurchaseOrder po) throws OrderException{

        // Generate orderId
        String orderId = UUID.randomUUID().toString().substring(0, 8);
        System.out.printf(">>>> OrderId: %s\n", orderId);

        po.setOrderId(orderId);

        // Create the purchaseOrder
        poRepo.insertPurchaseOrder(po);

        throw new OrderException("Exception for orderId %s".formatted(orderId));

        // Create the associated line items
        // liRepo.addLineItems(po.getLineItems(), orderId);

    } // Commit
    
}
