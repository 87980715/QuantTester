package data;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.struct.BarSeries;

public abstract class AbstractDataSource implements IDataSource {
	// �Ϻ��ڻ�������                                   ȼ��, �߲�
	protected final static String SQ[] = {"fu", "wr"};
	// �Ϻ��ڻ������� (ҹ��)              ͭ,   ��,   п,   Ǧ,   ��,   ��,   ��,   ��,���Ƹ�,�������,����,��Ȼ��
	protected final static String SY[] = {"cu", "al", "zn", "pb", "ni", "sn", "au", "ag", "rb", "hc", "bu", "ru"};
	// ������Ʒ������                                  ����, ���׵��, ��ά��,  ���ϰ�, ����, ���͵��ܶȾ���ϩ, ������ϩ, �۱�ϩ
	protected final static String DL[] = {"c",  "cs", "fb", "bb", "jd", "l",  "v",  "pp"};
	// ������Ʒ������  (ҹ��)          �ƴ�1��, �ƴ�2��, ����, ��ԭ��, �����, ұ��̿, ��ú, ����ʯ
	protected final static String DY[] = {"a",  "b",  "m",  "y",  "p",  "j",  "jm", "i"};
	// ֣����Ʒ������
	protected final static String ZZ[] = {"jr", "lr", "pm", "ri", "rs", "sf", "sm", "wh"};
	// ֣����Ʒ������ (ҹ��)
	protected final static String ZY[] = {"cf", "fg", "ma", "oi", "rm", "sr", "ta", "zc", "tc"};	// zcԭ��Ϊtc
	// �н���
	protected final static String ZJ[] = {"ic", "if", "ih", "t",  "tf"};

	protected List<Path> listSourceFiles(Path dir, String wildcard) {
		List<Path> result = new ArrayList<>();
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, wildcard)) {
			for (Path entry : stream) {
				result.add(entry);
			}
		} catch (DirectoryIteratorException | IOException ex) {
			// I/O error encounted during the iteration, the cause is an
			// IOException
			ex.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public Map<TIME_FRAME, BarSeries>[] multi_time_frame_bars = new Map[13];
	{
		for (int i = 0; i < 13; i++) {
			multi_time_frame_bars[i] = new HashMap<>();
		}
	}

	@Override
	public final BarSeries getBarSeries(final int month, final TIME_FRAME time_frame) {
		return multi_time_frame_bars[month].get(time_frame);
	}

}