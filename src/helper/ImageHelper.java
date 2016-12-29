package helper;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public final class ImageHelper {
	/**
	 * @see http://cping1982.blog.51cto.com/601635/130066
	 * @param bufferedimage
	 * @return ��Y�ᷭת��ͼ��
	 */
    public final static BufferedImage flipImage(final BufferedImage bufferedimage) {
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(w, h, bufferedimage
                .getColorModel().getTransparency())).createGraphics())
                .drawImage(bufferedimage, 0, 0, w, h, 0, h, w, 0, null);
        graphics2d.dispose();
        return img;
    }
    
    /**
     * ���ϲ�������ͼ��������������ǰ�ᣬ���ˮƽ����ϲ�����߶ȱ�����ȣ�����Ǵ�ֱ����ϲ�����ȱ�����ȡ�
     * mergeImage���������жϣ��Լ��жϡ�
     *
     * @see http://www.ablanxue.com/prone_3205_1.html
     * @param img1
     *            ���ϲ��ĵ�һ��ͼ
     * @param img2
     *            ���ϲ��ĵڶ���ͼ
     * @param isHorizontal
     *            Ϊtrueʱ��ʾˮƽ����ϲ���Ϊfalseʱ��ʾ��ֱ����ϲ�
     * @return ���غϲ����BufferedImage����
     * @throws IOException
     */
	public final static BufferedImage mergeImage(final BufferedImage img1, final BufferedImage img2, final boolean isHorizontal) {
		int w1 = img1.getWidth();
		int h1 = img1.getHeight();
		int w2 = img2.getWidth();
		int h2 = img2.getHeight();

		// ��ͼƬ�ж�ȡRGB
		int[] ImageArrayOne = new int[w1 * h1];
		ImageArrayOne = img1.getRGB(0, 0, w1, h1, ImageArrayOne, 0, w1); // ����ɨ��ͼ���и������ص�RGB��������
		int[] ImageArrayTwo = new int[w2 * h2];
		ImageArrayTwo = img2.getRGB(0, 0, w2, h2, ImageArrayTwo, 0, w2);

		// ������ͼƬ
		BufferedImage DestImage = null;
		if (isHorizontal) { // ˮƽ����ϲ�
			DestImage = new BufferedImage(w1 + w2, h1, BufferedImage.TYPE_INT_RGB);
			DestImage.setRGB(0, 0, w1, h1, ImageArrayOne, 0, w1); // �����ϰ벿�ֻ���벿�ֵ�RGB
			DestImage.setRGB(w1, 0, w2, h2, ImageArrayTwo, 0, w2);
		} else { // ��ֱ����ϲ�
			DestImage = new BufferedImage(w1, h1 + h2, BufferedImage.TYPE_INT_RGB);
			DestImage.setRGB(0, 0, w1, h1, ImageArrayOne, 0, w1); // �����ϰ벿�ֻ���벿�ֵ�RGB
			DestImage.setRGB(0, h1, w2, h2, ImageArrayTwo, 0, w2); // �����°벿�ֵ�RGB
		}

		return DestImage;
	}
}
