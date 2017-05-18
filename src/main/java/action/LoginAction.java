package action;

import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import service.user.AccountService;
import vo.user.AccountVO;

public class LoginAction extends ActionSupport {

	private AccountVO accountVO; // 表单中的姓名

	@Autowired
	private AccountService accountService;

	public String execute() {
		System.out.println(accountVO.getAccountID());
		System.out.println(accountVO.getPassword());

		if(accountService.login(accountVO)) {
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

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
}
