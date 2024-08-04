package nikitocheck.interview.t.moneytransfer;


import nikitocheck.interview.t.moneytransfer.domain.MoneyTransfer;
import nikitocheck.interview.t.moneytransfer.service.MoneyTransferService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MoneyTransferController {

    public MoneyTransferService moneyTransferService;

    public void transferMoney(@RequestBody MoneyTransfer moneyTransfer) {
        moneyTransferService.transferMoney(moneyTransfer);
    }

}
