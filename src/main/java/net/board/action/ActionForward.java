package net.board.action;

/* ������ �̵� �� �����̷�Ʈ�� ó���ϱ� ���� class
 * Servlet�̳� Controller���� ó���� ����� Ŭ���̾�Ʈ���� �˷��ִ� �� ����
 */
public class ActionForward {
	private boolean isRedirect=false; //Redirect�� ������� �ʰڴٴ°�
	//������ �̵� ����� ���� 'true'�� Redirect�� ��� 'false'�� �������� ���
	private String path=null;
	//�̵��� �������� ��θ� ����
	
	// Redirect ���θ� ��ȯ�ϴ� �޼ҵ�
	public boolean isRedirect() {
		return isRedirect;
	}
	
	// Redirect ���θ� �����ϴ� �޼ҵ� true, false�� ���� Redirect���� ���������� ����
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
	// �̵��� �������� ��θ� ��ȯ�ϴ� �޼���
	public String getPath() {
		return path;
	}
	
	// �̵��� �������� ��θ� �����ϴ� �޼���
	public void setPath(String path) {
		this.path = path;
	}
	
	
}