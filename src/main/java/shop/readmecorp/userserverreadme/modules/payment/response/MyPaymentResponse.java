package shop.readmecorp.userserverreadme.modules.payment.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.payment.dto.BookPaymentDTO;
import shop.readmecorp.userserverreadme.modules.payment.dto.MembershipPaymentDTO;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyPaymentResponse {
    List<BookPaymentDTO> bookPayments;
    List<MembershipPaymentDTO> membershipPayments;
}
