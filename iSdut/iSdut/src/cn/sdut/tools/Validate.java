package cn.sdut.tools;



/**
 * һЩ��������֤��
 * @author qinchuan
 *
 */
public class Validate {
	/**
	 * ��֤ѧ�ţ�10��11λ���֣�
	 * 11������ǰΪ10λ��12�����Ժ�Ϊ11λ
	 * @param str
	 * @return
	 */
	public static boolean checkUname(String str){
		return str.matches("\\d{10,11}");
	}
	
}
