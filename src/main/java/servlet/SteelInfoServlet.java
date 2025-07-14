package servlet;

import Dao.SteelInfoDao;
import model.SteelInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/steelInfo")
public class SteelInfoServlet extends HttpServlet {
    private SteelInfoDao steelInfoDao = new SteelInfoDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "add":
                showAddForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteSteelInfo(request, response);
                break;
            case "find":
                findSteelInfo(request, response);
                break;
            case "searchPage":
                showSearchPage(request, response);
                break;
            case "search":
                searchSteelInfo(request, response);
                break;
            default:
                listSteelInfo(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "add":
                addSteelInfo(request, response);
                break;
            case "edit":
                updateSteelInfo(request, response);
                break;
            default:
                listSteelInfo(request, response);
                break;
        }
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/addSteelInfo.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        SteelInfo steelInfo = steelInfoDao.findSteelInfoById(id);
        request.setAttribute("steelInfo", steelInfo);
        request.getRequestDispatcher("/editSteelInfo.jsp").forward(request, response);
    }

    private void addSteelInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            SteelInfo steelInfo = new SteelInfo();
            steelInfo.setSteelType(request.getParameter("steelType"));
            steelInfo.setSteelOrigin(request.getParameter("steelOrigin"));
            
            // 处理日期
            String productionDateStr = request.getParameter("productionDate");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date productionDate = dateFormat.parse(productionDateStr);
            steelInfo.setProductionDate(productionDate);
            
            steelInfo.setManufacturer(request.getParameter("manufacturer"));
            steelInfo.setPrice(Double.parseDouble(request.getParameter("price")));
            steelInfo.setVolume(Double.parseDouble(request.getParameter("volume")));

            steelInfoDao.insertSteelInfo(steelInfo);
            response.sendRedirect(request.getContextPath() + "/steelInfo?action=list");
        } catch (ParseException e) {
            e.printStackTrace();
            request.setAttribute("error", "日期格式错误");
            request.getRequestDispatcher("/addSteelInfo.jsp").forward(request, response);
        }
    }

    private void updateSteelInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            SteelInfo steelInfo = new SteelInfo();
            steelInfo.setId(Integer.parseInt(request.getParameter("id")));
            steelInfo.setSteelType(request.getParameter("steelType"));
            steelInfo.setSteelOrigin(request.getParameter("steelOrigin"));
            
            // 处理日期
            String productionDateStr = request.getParameter("productionDate");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date productionDate = dateFormat.parse(productionDateStr);
            steelInfo.setProductionDate(productionDate);
            
            steelInfo.setManufacturer(request.getParameter("manufacturer"));
            steelInfo.setPrice(Double.parseDouble(request.getParameter("price")));
            steelInfo.setVolume(Double.parseDouble(request.getParameter("volume")));

            steelInfoDao.updateSteelInfo(steelInfo);
            response.sendRedirect(request.getContextPath() + "/steelInfo?action=list");
        } catch (ParseException e) {
            e.printStackTrace();
            request.setAttribute("error", "日期格式错误");
            request.getRequestDispatcher("/editSteelInfo.jsp").forward(request, response);
        }
    }

    private void deleteSteelInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        steelInfoDao.deleteSteelInfo(id);
        response.sendRedirect(request.getContextPath() + "/steelInfo?action=list");
    }

    private void findSteelInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        SteelInfo steelInfo = steelInfoDao.findSteelInfoById(id);
        request.setAttribute("steelInfo", steelInfo);
        request.getRequestDispatcher("/viewSteelInfo.jsp").forward(request, response);
    }

    private void listSteelInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<SteelInfo> steelInfoList = steelInfoDao.findAll();
        request.setAttribute("steelInfoList", steelInfoList);
        request.getRequestDispatcher("/listSteelInfo.jsp").forward(request, response);
    }

    private void showSearchPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/searchSteelInfo.jsp").forward(request, response);
    }

    private void searchSteelInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchId = request.getParameter("searchId");
        String searchSteelType = request.getParameter("searchSteelType");
        String searchSteelOrigin = request.getParameter("searchSteelOrigin");
        
        // 如果提供了ID，优先按ID查询
        if (searchId != null && !searchId.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(searchId.trim());
                SteelInfo steelInfo = steelInfoDao.findSteelInfoById(id);
                
                if (steelInfo.getId() != 0) {
                    List<SteelInfo> steelInfoList = new ArrayList<>();
                    steelInfoList.add(steelInfo);
                    request.setAttribute("steelInfoList", steelInfoList);
                } else {
                    request.setAttribute("error", "未找到ID为 " + id + " 的钢材信息");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "请输入有效的ID");
            }
        } 
        // 如果提供了钢材类型，按类型查询
        else if (searchSteelType != null && !searchSteelType.trim().isEmpty()) {
            List<SteelInfo> steelInfoList = steelInfoDao.searchSteelInfo(searchSteelType.trim(), null, null, null);
            if (!steelInfoList.isEmpty()) {
                request.setAttribute("steelInfoList", steelInfoList);
            } else {
                request.setAttribute("error", "未找到类型包含 '" + searchSteelType.trim() + "' 的钢材信息");
            }
        } 
        // 如果提供了钢材产地，按产地查询
        else if (searchSteelOrigin != null && !searchSteelOrigin.trim().isEmpty()) {
            List<SteelInfo> steelInfoList = steelInfoDao.searchSteelInfo(null, searchSteelOrigin.trim(), null, null);
            if (!steelInfoList.isEmpty()) {
                request.setAttribute("steelInfoList", steelInfoList);
            } else {
                request.setAttribute("error", "未找到产地包含 '" + searchSteelOrigin.trim() + "' 的钢材信息");
            }
        } 
        // 如果没有任何查询条件
        else {
            request.setAttribute("error", "请至少输入一个查询条件");
        }
        
        request.getRequestDispatcher("/searchSteelInfo.jsp").forward(request, response);
    }
} 