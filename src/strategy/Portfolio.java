package strategy;

public class Portfolio {
	// http://www.shfe.com.cn/bourseService/activity/basics/211231978.html
	
	public float margin = 0.0f;
	
	// TODO ��ȷ��?
	public float getBalance(final float price) {
		float long_profit = (price - long_open_price) * long_volume;
		float short_profit = (short_open_price - price) * short_volume;
		
		return this.margin + long_profit + short_profit;
	}
	
	// TODO ��ȷ��?
	public float getAvailableMargin(final float price) {
		float long_frozen = long_open_price * long_volume * margin_ratio;
		float short_frozen = short_open_price * short_volume * margin_ratio;
		
		return getBalance(price) - long_frozen - short_frozen;
	}

	// ���׳ɱ�, Ŀǰֻ����������(˫��) + ���
	private void cost(final float amount) {
		this.margin -= (amount * commission_ratio);
	}

	public float long_volume = 0.0f;
	public float long_open_price = 0.0f;
	public float short_volume = 0.0f;
	public float short_open_price = 0.0f;
	
	protected float margin_ratio = 0.12f;
	protected float commission_ratio = 0.001f;
	
	public final float getMargin_ratio() {
		return margin_ratio;
	}

	public final void setMargin_ratio(final float margin_ratio) {
		this.margin_ratio = margin_ratio;
	}

	public final float getCommission_ratio() {
		return commission_ratio;
	}

	public final void setCommission_ratio(final float commission_ratio) {
		this.commission_ratio = commission_ratio;
	}

	public Portfolio(float init_cash) {
		this.margin = init_cash;
		this.long_volume = 0.0f;
		this.short_volume = 0.0f;
	}

	public static boolean g_print_trade_log = false;
	
	public static final void printlog(String log) {	// FIXME
		if (g_print_trade_log)
			System.out.println(log);
	}
	
	public boolean openLong(float price, float volume) {
		// TODO ��鱣֤��
		if (margin > 100.0f) {
			long_open_price = price;
			long_volume = volume;
			cost(price * volume);
			long_trades ++;
			printlog("OpenLong:   price = " + price + ", volume = " + volume);
			return true;
		} else {
			return false;
		}
	}

	public boolean openShort(float price, float volume) {
		// TODO ��鱣֤��
		if (margin > 100.0f) {
			short_open_price = price;
			short_volume = volume;
			cost(price * volume);
			short_trades ++;
			printlog("OpenShort:  price = " + price + ", volume = " + volume);
			return true;
		} else {
			return false;
		}
	}

	public boolean closeLong(float price, float volume) {
		if (long_volume > 0.01f) {
			float long_profit = (price - long_open_price) * volume;
			margin += long_profit;
			long_volume -= volume;
			if (long_volume < 0.01f) {
				long_volume = 0.0f;
				long_open_price = 0.0f;
			}
			cost(price * volume);
			printlog("CloseLong:  price = " + price + ", volume = " + volume + ", long_profit  = " + long_profit);
			return true;
		} else {
			return false;
		}
	}

	public boolean closeShort(float price, float volume) {
		if (short_volume > 0.01f) {
			float short_profit = (short_open_price - price) * volume;
			margin += short_profit;
			short_volume -= volume;
			if (short_volume < 0.01f) {
				short_volume = 0.0f;
				short_open_price = 0.0f;
			}
			cost(price * volume);
			printlog("CloseShort: price = " + price + ", volume = " + volume + ", short_profit = " + short_profit);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean hasNoPosition() {
		return (this.long_volume < 0.01f && this.short_volume < 0.01f);
	}
	
	public int long_trades = 0;
	public int short_trades = 0;
}
