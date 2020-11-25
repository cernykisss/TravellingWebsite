package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteImage;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RouteImageImpl implements RouteImage {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<RouteImage> findById(int rid) {
        String sql = "select * from tab_route_img where rid = ?";
        List<RouteImage> list = template.query(sql, new BeanPropertyRowMapper<RouteImage>(RouteImage.class), rid);
        return list;
    }
}
