package vbsp.ims.font;

public class ImsConverter {

	public static void main(String[] args) {
		String str = "Hôm nay câu cá một mình tại trường, hỏi ai đã câu được con nào hay chưa.";
		System.out.println("UNICODE: " + str);
		str = convert_font(FontType.UNICODE, FontType.VNI_WINDOWS, str);
		str = convert_font(FontType.UNKNOW, FontType.VNI_WINDOWS, str);
		str = convert_font(FontType.UNICODETH, str);
		str = convert_font(FontType.TCVN3, str);
		str = convert_font(FontType.UNICODE, str);
		str = convert_font(FontType.ACSII, str);
	}

	public static String convert_font(FontType to, String str) {
		FontType from = FontConverter.findFontType(str);
		return convert_font(from, to, str);
	}

	public static String convert_font(FontType from, FontType to, String str) {
		str = FontConverter.convert(from, to, str);
//		System.out.println("Convert " + from.toString() + " -> "
//				+ to.toString() + "=" + str);
		return str;
	}
}
