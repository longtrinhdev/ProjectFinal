package MVP;

public class LoginPresenter {
    private  AdminPresenter adminPresenter;

    public LoginPresenter(AdminPresenter adminPresenter) {
        this.adminPresenter = adminPresenter;
    }
    // Hàm
    public void loginAdmin(AdminLogin admin) {
        if (admin.isCheckEmail() && admin.ischeckPassword()) {
            adminPresenter.loginAdminSuccess();
        }else {
            adminPresenter.loginAdminFailed();
        }
    }
}
