package spittr.data;

import java.util.List;

public interface SpittleRepository {
	/**
	 * 
	 * @param max	代表所返回的Spittle中，SpittleID属性的最大值
	 * @param count	表示要返回多少个Spittle对象
	 * @return
	 */
	List<Spittle> findSpittles(long max, int count);

	Spittle findOne(Long id);
}
