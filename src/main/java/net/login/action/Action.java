package net.login.action;

import javax.servlet.http.*;

/* Action �������̽��� ������ Ŭ������ ��Ʈ�ѷ��� ������ �����ϸ�, Ŭ���̾�Ʈ�� ��û�� ������ ó���Ͽ� �信 ����
 * �ش� Ŭ������ �����ϴ� ���� ���� Action Ŭ������ ���� �� ����. �̷��� �ϸ� �� ��û�� ���� �ٸ� �۾��� �����ϴ� ���� ������ ���� �� �ִ� �������� Ȯ�强�� ����
 * ���� ���, �Խñ� �߰�, ����, ���� �� �پ��� ������ ó���ϴ� Action Ŭ������ ���� ����� ������ �� ����
 */
public interface Action {
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response)
			throws Exception;
}
