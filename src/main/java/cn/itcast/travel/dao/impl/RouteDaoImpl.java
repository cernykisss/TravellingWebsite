package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findTotalCount(int cid, String rname) {
        String sql = "select count(*) from tab_route where 1=1";
        StringBuilder stringBuilder = new StringBuilder(sql);
        List params = new ArrayList();

        if (cid != 0) {
            stringBuilder.append(" and cid = ?");
            params.add(cid);
        }
        if (rname != null && rname.length() > 0) {
            stringBuilder.append(" and rname like ?");
            params.add("%"+rname+"%");
        }
        sql = stringBuilder.toString();
        Integer count = template.queryForObject(sql, Integer.class, params.toArray());
        return count;
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize, String rname) {
        String sql = "select * from tab_route where 1 = 1 ";
        StringBuilder stringBuilder = new StringBuilder(sql);
        ArrayList params = new ArrayList();
        if (cid != 0) {
            stringBuilder.append("and cid = ?");
            params.add(cid);
        }
        if (rname != null && rname.length() > 0) {
            stringBuilder.append("and rname like ? ");
            params.add("%" + rname + "%");
        }
        stringBuilder.append("limit ?, ?");
        sql = stringBuilder.toString();
        params.add(start);
        params.add(pageSize);
        List<Route> routes = template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), params.toArray());
        return routes;
    }

    @Override
    public Route findOne(int rid) {
        String sql = "select * from tab_route where rid = ?";
        Route route = template.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class), rid);
        return route;
    }
}
