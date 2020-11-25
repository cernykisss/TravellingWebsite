package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.RouteImage;
import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.RouteImageImpl;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {

    private RouteDao routeDao = new RouteDaoImpl();
    private RouteImage routeImage = new RouteImageImpl();
    private SellerDao sellerDao = new SellerDaoImpl();

    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {
        PageBean<Route> routePageBean = new PageBean<>();
        routePageBean.setCurrentPage(currentPage);
        routePageBean.setPageSize(pageSize);
        int totalCount = routeDao.findTotalCount(cid, rname);
        routePageBean.setTotalCount(totalCount);
        int start = (currentPage - 1) * pageSize;
        List<Route> list = routeDao.findByPage(cid, start, pageSize, rname);
        routePageBean.setList(list);
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
        routePageBean.setTotalPage(totalPage);
        return routePageBean;
    }

    @Override
    public Route findOne(String rid) {
        Route route = routeDao.findOne(Integer.parseInt(rid));
        List<RouteImage> routeImageList = routeImage.findById(Integer.parseInt(rid));
        route.setRouteImgList(routeImageList);
        Seller seller = sellerDao.findById(route.getSid());
        route.setSeller(seller);
        return route;
    }


}
