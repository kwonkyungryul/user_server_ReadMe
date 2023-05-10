package shop.readmecorp.userserverreadme.modules.bootpay.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.readmecorp.userserverreadme.modules.bootpay.dto.BootPayDTO;
import shop.readmecorp.userserverreadme.modules.bootpay.entity.BootPayLog;
import shop.readmecorp.userserverreadme.modules.bootpay.entity.BootPayMaster;
import shop.readmecorp.userserverreadme.modules.bootpay.entity.CardData;
import shop.readmecorp.userserverreadme.modules.bootpay.entity.Metadata;
import shop.readmecorp.userserverreadme.modules.bootpay.repository.BootPayLogRepository;
import shop.readmecorp.userserverreadme.modules.bootpay.repository.BootPayRepository;
import shop.readmecorp.userserverreadme.modules.bootpay.repository.CardDataRepository;
import shop.readmecorp.userserverreadme.modules.bootpay.repository.MetadataRepository;
import shop.readmecorp.userserverreadme.modules.bootpay.request.BootPaySaveRequest;

@Service
public class BootPayService {

    private final BootPayRepository bootPayRepository;

    private final CardDataRepository cardDataRepository;

    private final MetadataRepository metadataRepository;

    private final BootPayLogRepository bootPayLogRepository;

    public BootPayService(BootPayRepository bootPayRepository, CardDataRepository cardDataRepository, MetadataRepository metadataRepository, BootPayLogRepository bootPayLogRepository) {
        this.bootPayRepository = bootPayRepository;
        this.cardDataRepository = cardDataRepository;
        this.metadataRepository = metadataRepository;
        this.bootPayLogRepository = bootPayLogRepository;
    }

    @Transactional
    public BootPayDTO save(BootPaySaveRequest request) {
        BootPayLog logEntity = BootPayLog.builder()
                .id(null)
                .sandbox(request.isSandbox())
                .pg(request.getPg())
                .method(request.getMethod())
                .status(request.getStatus())
                .receiptId(request.getReceiptId())
                .orderId(request.getOrderId())
                .price(request.getPrice())
                .taxFree(request.getTaxFree())
                .cancelledPrice(request.getCancelledPrice())
                .cancelledTaxFree(request.getCancelledTaxFree())
                .orderName(request.getOrderName())
                .companyName(request.getCompanyName())
                .gatewayUrl(request.getGatewayUrl())
                .methodSymbol(request.getMethodSymbol())
                .methodOrigin(request.getMethodOrigin())
                .methodOriginSymbol(request.getMethodOriginSymbol())
                .purchasedAt(request.getPurchasedAt())
                .cancelledAt(request.getCancelledAt())
                .requestedAt(request.getRequestedAt())
                .statusLocale(request.getStatusLocale())
                .receiptUrl(request.getReceiptUrl())
                .build();
        bootPayLogRepository.save(logEntity);

        BootPayMaster bootPay = BootPayMaster.builder()
                .id(null)
                .sandbox(request.isSandbox())
                .pg(request.getPg())
                .method(request.getMethod())
                .status(request.getStatus())
                .receiptId(request.getReceiptId())
                .orderId(request.getOrderId())
                .price(request.getPrice())
                .taxFree(request.getTaxFree())
                .cancelledPrice(request.getCancelledPrice())
                .cancelledTaxFree(request.getCancelledTaxFree())
                .orderName(request.getOrderName())
                .companyName(request.getCompanyName())
                .gatewayUrl(request.getGatewayUrl())
                .methodSymbol(request.getMethodSymbol())
                .methodOrigin(request.getMethodOrigin())
                .methodOriginSymbol(request.getMethodOriginSymbol())
                .purchasedAt(request.getPurchasedAt())
                .cancelledAt(request.getCancelledAt())
                .requestedAt(request.getRequestedAt())
                .statusLocale(request.getStatusLocale())
                .receiptUrl(request.getReceiptUrl())
                .build();
        bootPayRepository.save(bootPay);

        Metadata metaEntity = request.getMetadata().toEntity();
        metaEntity.setBootPayMaster(bootPay);

        CardData cardEntity = request.getCardData().toEntity();
        cardEntity.setBootPayMaster(bootPay);

        Metadata metaSave = metadataRepository.save(metaEntity);
        CardData cardSave = cardDataRepository.save(cardEntity);


        return new BootPayDTO(bootPay.toDTO(), cardSave.toDTO(), metaSave.toDTO());
    }
}
