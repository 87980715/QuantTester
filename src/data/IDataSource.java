package data;

import data.struct.BarSeries;

public interface IDataSource {
	// TODO ʵ������ģ������ / ����ʵ���ݴ���
	BarSeries getBarSeries(int month, TIME_FRAME time_frame);	// �ڻ�: month = 1 to 12, 0 ��ʾ����, ��Ʊ: month = 0

}