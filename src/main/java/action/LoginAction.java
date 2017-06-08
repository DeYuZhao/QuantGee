package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.stock.CollectStockService;
import service.user.AccountService;
import vo.stock.StockCurrentVO;
import vo.user.AccountVO;

import java.util.ArrayList;
import java.util.Map;

@Controller
public class LoginAction extends ActionSupport {

    //	private AccountVO accountVO = new AccountVO(); // 表单中的姓名
    private AccountVO accountVO; // 表单中的姓名


    private ArrayList<StockCurrentVO> stockCurrentVOS; //用户收藏的股票


    @Autowired
    private AccountService accountService;
    @Autowired
    private CollectStockService collectStockService;

    //	@Result({
//			@Result(name = "success" ,value = "/view/ucenter/user_center.jsp"),
//			@Result(name = "fail" ,value = "/view/ucenter/login.jsp")
//	})
    public String login() {
        if (accountService.login(accountVO)) {
            ActionContext actionContext = ActionContext.getContext();
            Map session = actionContext.getSession();
            session.put("USER_NAME","Test User");
            String a=(String)session.get("USER_NAME");
            System.out.println(a);
//            stockCurrentVOS = collectStockService.getCollectedStocks(accountVO.getAccountID());
            stockCurrentVOS = new ArrayList<>();
            StockCurrentVO stockCurrentVO1 = new StockCurrentVO("111111","wddygg","zg","123",1,2,3,4,5,6,7,8,9,1,2,1);
            StockCurrentVO stockCurrentVO2 = new StockCurrentVO("222222","wddygg","zg","123",1,2,3,4,5,6,7,8,9,1,2,1);
            StockCurrentVO stockCurrentVO3 = new StockCurrentVO("333333","wddygg","zg","123",1,2,3,4,5,6,7,8,9,1,2,1);
            stockCurrentVOS.add(stockCurrentVO1);
            stockCurrentVOS.add(stockCurrentVO2);
            stockCurrentVOS.add(stockCurrentVO3);
            return SUCCESS;
        } else {
            return "fail";
        }

    }

    public String logout() {
        System.out.println("yes");
        if (accountService.login(accountVO)) {
            return SUCCESS;
        } else {
            return "fail";
        }

    }

    public String register() {

        if (accountService.register(accountVO)) {
            return SUCCESS;
        } else {
            return "fail";
        }
    }


    public AccountVO getAccountVO() {
        return accountVO;
    }

    public void setAccountVO(AccountVO accountVO) {
        this.accountVO = accountVO;
    }

    public ArrayList<StockCurrentVO> getStockCurrentVOS() {
        return stockCurrentVOS;
    }

    public void setStockCurrentVOS(ArrayList<StockCurrentVO> stockCurrentVOS) {
        this.stockCurrentVOS = stockCurrentVOS;
    }

}
