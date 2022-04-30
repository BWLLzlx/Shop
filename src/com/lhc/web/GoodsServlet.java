package com.lhc.web;

import com.lhc.pojo.Goods;
import com.lhc.pojo.Operation;
import com.lhc.pojo.Page;
import com.lhc.service.GoodsService;
import com.lhc.service.OperationService;
import com.lhc.service.impl.GoodsServiceImpl;
import com.lhc.service.impl.OperationServiceImpl;
import com.lhc.utils.WebUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class GoodsServlet extends BaseServlet{

    private GoodsService goodsService = new GoodsServiceImpl();
    private OperationService operationService = new OperationServiceImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Goods goods = WebUtils.copyParamToBean(req.getParameterMap(),new Goods());
        goodsService.addGoods(goods);
        //用重定向，发起新的请求，回到展示页面
        resp.sendRedirect(req.getContextPath()+"/manager/goodsServlet?action=page");
    }

    protected void addWithPhoto(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //判断是否多段
        if(ServletFileUpload.isMultipartContent(req)){
            Goods goods = new Goods();
            Operation operation = new Operation();
            int sellerId = 1;
            //创建工厂实现类
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            //创建用于解析数据的工具类
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            //解析上传的数据，得到每一个表单项FileItem
            try {
                List<FileItem> list = servletFileUpload.parseRequest(req);
                for (FileItem fileItem : list) {
                    System.out.println(fileItem.getFieldName());
                }
                //判断每个表单项的类型
                for (FileItem fileItem : list) {
                    if(fileItem.isFormField()){
                        //普通表单项
                        System.out.println("普通表单项"+ fileItem.getFieldName() + ":" + fileItem.getString("UTF-8"));

                        if("name".equals(fileItem.getFieldName())){
                            goods.setName(fileItem.getString("UTF-8"));
                        }else if ("price".equals(fileItem.getFieldName())){
                            goods.setPrice(WebUtils.parseInt(fileItem.getString("UTF-8"),0));
                        }else if ("sales".equals(fileItem.getFieldName())){
                            goods.setSales(WebUtils.parseInt(fileItem.getString("UTF-8"),0));
                        }else if ("stock".equals(fileItem.getFieldName())){
                            goods.setStock(WebUtils.parseInt(fileItem.getString("UTF-8"),0));
                        }else if ("sellerId".equals(fileItem.getFieldName())){
                            goods.setSellerId(WebUtils.parseInt(fileItem.getString("UTF-8"),1));
                            sellerId = WebUtils.parseInt(fileItem.getString("UTF-8"),1);
                        }else if ("categoryId".equals(fileItem.getFieldName())){
                            goods.setCategoryId(WebUtils.parseInt(fileItem.getString("UTF-8"),6));
                        }else if ("ip".equals(fileItem.getFieldName())){
                            operation.setIp(fileItem.getString("UTF-8"));
                        }else if ("date".equals(fileItem.getFieldName())){
                            operation.setDate(fileItem.getString("UTF-8"));
                        }else if ("role".equals(fileItem.getFieldName())){
                            operation.setRole(fileItem.getString("UTF-8"));
                        }else if ("roleId".equals(fileItem.getFieldName())){
                            operation.setRoleId(WebUtils.parseInt(fileItem.getString("UTF-8"),0));
                        }else if ("operate".equals(fileItem.getFieldName())){
                            operation.setOperate(fileItem.getString("UTF-8"));
                        }else if ("target".equals(fileItem.getFieldName())){
                            operation.setTarget(fileItem.getString("UTF-8"));
                        }
                    }else {
                        //上传的文件
                        String uuid = WebUtils.getUUID();
                        if(fileItem.getName()!=null && !"".equals(fileItem.getName())){
                            System.out.println("上传的文件名：" +uuid+"_"+ fileItem.getName());
//                            fileItem.write(new File("/usr/local/tomcat7/apache-tomcat-7.0.82/webapps/Shop/static/img/"+uuid+".jpg"));
//                            goods.setImgPath("static/img/"+uuid+".jpg");
                            fileItem.write(new File("d:\\Desktop\\Shop\\web\\static\\img\\"+uuid+".jpg"));
                            goods.setImgPath("static/img/"+uuid+".jpg");
                        }else{
                            System.out.println("上传文件为空");
                            goods.setImgPath("static/img/1.png");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            goodsService.addGoods(goods);
            operationService.addOperation(operation);

            resp.sendRedirect(req.getContextPath()+"/manager/goodsServlet?action=page&sellerId="+sellerId);
        }
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int sellerId = WebUtils.parseInt(req.getParameter("sellerId"),1);
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        goodsService.deleteGoods(id);

        Operation operation = WebUtils.copyParamToBean(req.getParameterMap(), new Operation());
        operationService.addOperation(operation);

        resp.sendRedirect(req.getContextPath()+"/manager/goodsServlet?action=page&sellerId="+sellerId);
    }

    protected void getGoods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Goods goods = goodsService.queryGoodsById(id);
        req.setAttribute("goods",goods);
        req.getRequestDispatcher("/pages/manager/goods_edit.jsp").forward(req,resp);
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Goods goods = WebUtils.copyParamToBean(req.getParameterMap(),new Goods());
        goodsService.updateGoods(goods);
        //用重定向，发起新的请求，回到展示页面
        resp.sendRedirect(req.getContextPath()+"/manager/goodsServlet?action=page");
    }

    protected void updateWithPhoto(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //判断是否多段
        if(ServletFileUpload.isMultipartContent(req)){
            Goods goods = new Goods();
            Operation operation = new Operation();
            int id = 0;
            int sellerId = 1;
            //创建工厂实现类
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            //创建用于解析数据的工具类
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            //解析上传的数据，得到每一个表单项FileItem
            try {
                List<FileItem> list = servletFileUpload.parseRequest(req);
                for (FileItem fileItem : list) {
                    System.out.println(fileItem.getFieldName()+":"+fileItem.isFormField());
                }
                //判断每个表单项的类型
                for (FileItem fileItem : list) {
                    if (fileItem.isFormField()) {
                        //普通表单项
                        System.out.println("普通表单项" + fileItem.getFieldName() + ":" + fileItem.getString("UTF-8"));

                        if ("id".equals(fileItem.getFieldName())) {
                            goods.setId(WebUtils.parseInt(fileItem.getString("UTF-8"), 0));
                            id = WebUtils.parseInt(fileItem.getString("UTF-8"), 0);
                        } else if ("name".equals(fileItem.getFieldName())) {
                            goods.setName(fileItem.getString("UTF-8"));
                        } else if ("price".equals(fileItem.getFieldName())) {
                            goods.setPrice(WebUtils.parseInt(fileItem.getString("UTF-8"), 0));
                        } else if ("sales".equals(fileItem.getFieldName())) {
                            goods.setSales(WebUtils.parseInt(fileItem.getString("UTF-8"), 0));
                        } else if ("stock".equals(fileItem.getFieldName())) {
                            goods.setStock(WebUtils.parseInt(fileItem.getString("UTF-8"), 0));
                        } else if ("sellerId".equals(fileItem.getFieldName())) {
                            goods.setSellerId(WebUtils.parseInt(fileItem.getString("UTF-8"), 1));
                            sellerId = WebUtils.parseInt(fileItem.getString("UTF-8"), 1);
                        } else if ("categoryId".equals(fileItem.getFieldName())) {
                            goods.setCategoryId(WebUtils.parseInt(fileItem.getString("UTF-8"), 6));
                        } else if ("ip".equals(fileItem.getFieldName())) {
                            operation.setIp(fileItem.getString("UTF-8"));
                        } else if ("date".equals(fileItem.getFieldName())) {
                            operation.setDate(fileItem.getString("UTF-8"));
                        } else if ("role".equals(fileItem.getFieldName())) {
                            operation.setRole(fileItem.getString("UTF-8"));
                        } else if ("roleId".equals(fileItem.getFieldName())) {
                            operation.setRoleId(WebUtils.parseInt(fileItem.getString("UTF-8"), 0));
                        } else if ("operate".equals(fileItem.getFieldName())) {
                            operation.setOperate(fileItem.getString("UTF-8"));
                        } else if ("target".equals(fileItem.getFieldName())) {
                            operation.setTarget(fileItem.getString("UTF-8"));
                        }



                    }else {
                        //上传的文件
                        String uuid = WebUtils.getUUID();
                        if(fileItem.getName()!=null && !"".equals(fileItem.getName())){
                            System.out.println("上传的文件名：" +uuid+"_"+ fileItem.getName());
                            //下面这是windows的路径，最后部署的时候，需要修改路径为linux的路径
                            fileItem.write(new File("d:\\Desktop\\Shop\\web\\static\\img\\"+uuid+".jpg"));
                            goods.setImgPath("static/img/"+uuid+".jpg");
                            //这是linux项目的图片存储的路径
                            // /usr/local/tomcat7/apache-tomcat-7.0.82/webapps/Shop_war
                            //又多弄了一个地址，就是Shop，把下面的链接改了，要弄的话，统一成Shop或者统一成Shop_war
//                            fileItem.write(new File("/usr/local/tomcat7/apache-tomcat-7.0.82/webapps/Shop/static/img/"+uuid+".jpg"));
//                            goods.setImgPath("static/img/"+uuid+".jpg");
                        }else{
                            System.out.println("上传文件为空");
                            if ("updateWithPhoto".equals(req.getParameter("action"))){
                                System.out.println("这是在修改商品");
                                System.out.println("这是修改商品的id：" + id);
                                String path = goodsService.queryGoodsById(id).getImgPath();
                                System.out.println("这是这个id的图片路径："+ path);
                                goods.setImgPath(path);
                            }

                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            goodsService.updateGoods(goods);
            operationService.addOperation(operation);

            resp.sendRedirect(req.getContextPath()+"/manager/goodsServlet?action=page&sellerId="+sellerId);
        }
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Goods> goods = goodsService.queryGoods();
        req.setAttribute("goods",goods);
        req.getRequestDispatcher("/pages/manager/goods_manager.jsp").forward(req,resp);
    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int sellerId = WebUtils.parseInt(req.getParameter("sellerId"), 0);
        if(sellerId == 0){
            Page<Goods> page = goodsService.page(pageNo,pageSize);
            page.setUrl("manager/goodsServlet?action=page");
            req.setAttribute("page",page);
            req.getRequestDispatcher("/pages/manager/goods_manager.jsp").forward(req,resp);
        }else{//如果不是0就说明是seller登录
            Page<Goods> page = goodsService.pageBySellerId(pageNo,pageSize,sellerId);
            page.setUrl("manager/goodsServlet?action=page&sellerId="+sellerId);
            req.setAttribute("page",page);
            req.getRequestDispatcher("/pages/manager/goods_manager.jsp").forward(req,resp);
        }
    }

//    把管理员或者商家的搜索改了一下，改成utf-8去解码，不改提交方式，还是get
//    这个办法失败了
//    所以最终还是用post就行，不需要设置这么多
    protected void pageByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        String goodsName = req.getParameter("goodsName");

        //记录操作信息
        Operation operation = WebUtils.copyParamToBean(req.getParameterMap(), new Operation());
        System.out.println(operation);
        operationService.addOperation(operation);

        int sellerId = WebUtils.parseInt(req.getParameter("sellerId"), 0);
        if(sellerId == 0){//表示管理员登录
            Page<Goods> page = goodsService.pageByName(pageNo,pageSize,goodsName);
            StringBuilder stringBuilder = new StringBuilder("manager/goodsServlet?action=pageByName");
            if (goodsName != null){
                stringBuilder.append("&goodsName=").append(goodsName);
            }

            page.setUrl(stringBuilder.toString());
            req.setAttribute("page",page);
            req.setAttribute("goodsName",goodsName);
            req.getRequestDispatcher("/pages/manager/goods_manager.jsp").forward(req,resp);
        }else{//如果不是0就说明是seller登录
            Page<Goods> page = goodsService.pageBySellerIdAndGoodsName(pageNo,pageSize,sellerId,goodsName);
            StringBuilder stringBuilder = new StringBuilder("manager/goodsServlet?action=pageByName&sellerId="+sellerId);
            if (goodsName != null){
                stringBuilder.append("&goodsName=").append(goodsName);
            }
            page.setUrl(stringBuilder.toString());
            req.setAttribute("page",page);
            req.setAttribute("goodsName",goodsName);
            req.getRequestDispatcher("/pages/manager/goods_manager.jsp").forward(req,resp);
        }
    }
}
