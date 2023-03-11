<p align="center">
 <img width="100px" src="https://res.cloudinary.com/anuraghazra/image/upload/v1594908242/logo_ccswme.svg" align="center" alt="GitHub Readme Stats" />
 <h2 align="center"CRUD Hibernate với JSP, Servlet</h2>
</p>
<div align="center">
 <table >
  <theader>
  <th>
   Nội Dung 
   </th>
   </theader>
  <tbody>
  <td>
   <p> <a href="#WhatisHibernate">1: Hibernate là gì?</a> </p>
   <p><a href="#WhatisJSP">2: JSP là gì?</a> </p>
   <p><a href="#WhatisServlet">3:Triển khai CRUD Hibernate với JSP, Servlet?</a></p>
   </td>
   </tbody>
   </table>
</div>

<h1 id="WhatisHibernate">Hibernate là gì?</h1>

Hibernate là một khung công tác Java triển khai mẫu thiết kế ORM (Ánh xạ quan hệ đối tượng). Nó được sử dụng để ánh xạ các đối tượng java vào cơ sở dữ liệu quan hệ(relationship). Nó sử dụng nội bộ JDBC (Kết nối cơ sở dữ liệu Java), JTA (API giao dịch Java) và JNDI (Giao diện thư mục và đặt tên Java). Nó giúp làm cho các đối tượng java tồn tại trong cơ sở dữ liệu mà không mất trạng thái của chúng, do đó, được đặt tên là Hibernate. Nó có thể được sử dụng để thực hiện tất cả các hoạt động CRUD mà không cần phải viết các truy vấn SQL. CRUD đề cập đến các hoạt động cơ sở dữ liệu

<h1 id="WhatisJSP"> JSP là gì?</h1>

Java Server Pages (JSP) là một công nghệ cho phép các nhà phát triển tạo các trang web động bằng cách sử dụng kết hợp mã HTML, XML và Java. Các trang JSP được thực thi trên máy chủ web và kết quả đầu ra được gửi đến trình duyệt web của máy khách. JSP cung cấp một cách để dễ dàng truy cập mã Java và các đối tượng từ bên trong một trang web, giúp đơn giản hóa việc tạo các trang web động. Các trang JSP thường được sử dụng cùng với các servlet Java, xử lý xử lý dữ liệu và các yêu cầu của máy khách. JSP là một phần của nền tảng Java EE và được hầu hết các máy chủ web và bộ chứa servlet hỗ trợ.

<h1 id="WhatisServlet"> JSP là gì?</h1>

## Cấu trúc thư mục 

![image](https://user-images.githubusercontent.com/109157942/224464239-3051d47a-0c96-4fc5-9837-e18d96698b04.png)

## Thiết lập dự án 

Bạn cần bao gồm các phụ thuộc sau trong tập lệnh pom.xml 

```
 <dependencies>
        <dependency>
            <groupId>jakarta.servlet</groupId>
            <artifactId>jakarta.servlet-api</artifactId>
            <version>5.0.0</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>com.microsoft.sqlserver</groupId>
            <artifactId>mssql-jdbc</artifactId>
            <version>9.4.1.jre16</version>
        </dependency>

        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-search-orm</artifactId>
            <version>5.8.2.Final</version>
        </dependency>
        <dependency>
            <groupId>jakarta.persistence</groupId>
            <artifactId>jakarta.persistence-api</artifactId>
            <version>3.1.0</version>
        </dependency>
        <dependency>
            <groupId>jakarta.xml.bind</groupId>
            <artifactId>jakarta.xml.bind-api</artifactId>
            <version>2.3.2</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.apache.taglibs/taglibs-standard-impl -->
        <dependency>
            <groupId>org.apache.taglibs</groupId>
            <artifactId>taglibs-standard-impl</artifactId>
            <version>1.2.5</version>
            <scope>runtime</scope>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.apache.taglibs/taglibs-standard-spec -->
        <dependency>
            <groupId>org.apache.taglibs</groupId>
            <artifactId>taglibs-standard-spec</artifactId>
            <version>1.2.5</version>
        </dependency>
        <dependency>
            <groupId>jakarta.servlet.jsp.jstl</groupId>
            <artifactId>jakarta.servlet.jsp.jstl-api</artifactId>
            <version>3.0.0</version>
        </dependency>
        <dependency>
            <groupId>org.glassfish.web</groupId>
            <artifactId>jakarta.servlet.jsp.jstl</artifactId>
            <version>2.0.0</version>
        </dependency>
    </dependencies>
```

## Tạo các lớp thực thể 
 
 > SanPham.java

```

@Table(name="san_pham")
@Entity
public class SanPham {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "ten")
    private String ten;
    
    @Column(name = "gia")
    private float gia;

    public SanPham(int id, String ten, float gia) {
        this.id = id;
        this.ten = ten;
        this.gia = gia;
    }

    public SanPham() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    
    
}


```

> chú thích 

@Entity xác định lớp hiện tại là một entity.\
@Table xác định tên bảng ánh xạ sang.\
@Id xác định thuộc tính hiện tại là ID trong bảng CSDL.\
@GeneratedValue xác định kiểu sinh khóa chính tự động, ở đây là IDENTITY.\
@Column xác định thuộc tính hiện tại là một cột trong CSDL.

## Tạo database trong mysql 

```

create database demohibernate

```

## Config hibernate 

> HibernateUtil.java

```

private static final SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=demohibernate;encrypt=true;trustServerCertificate=true");
        properties.put(Environment.USER, "demo");
        properties.put(Environment.PASS, "1234567890");
        properties.put(Environment.SHOW_SQL, "true");
         //gen DB tự động
        properties.put(Environment.HBM2DDL_AUTO, "create");

        conf.setProperties(properties);
        conf.addAnnotatedClass(SanPham.class);
        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);

    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }

    public static void main(String[] args) {
        System.out.println(getFACTORY());
    }

```

> chú ý 

 Thay đổi user name và password của sql server của mk tại đây 

```
properties.put(Environment.USER, "demo");
properties.put(Environment.PASS, "1234567890");

```

Bạn có thể tạo table trực tiếp trong sql server hoặc để hibernate tự tạo table 

```

tạo table tự động(sau mỗi lần chạy sẽ tạo lại table)
 properties.put(Environment.HBM2DDL_AUTO, "create");
 
 bỏ tạo table tự động
  properties.put(Environment.HBM2DDL_AUTO, "none");

```

## Hiển thị Sản phẩm 

> SanPhamRespone.java

```
 private int id;
    private String ten;
    private float gia;

    public SanPhamRespone(int id, String ten, float gia) {
        this.id = id;
        this.ten = ten;
        this.gia = gia;
    }

    public SanPhamRespone() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }
```

> SanPhamRepository.java

```
public class SanPhamRepository {

    public List<SanPhamRespone> getAll() {
        List<SanPhamRespone> sanPhams = new ArrayList<>();
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("SELECT new com.example.demo.response.SanPhamRespone(sp.id, sp.ten, sp.gia) FROM SanPham sp", SanPhamRespone.class);
            sanPhams = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return sanPhams;
    }
  }
```

> SanPhamService.java

```
public interface SanPhamService {
    
    List<SanPhamRespone> getAll();
  }
```

> SanPhamSerViceImpl.java

```
public class SanPhamSerViceImpl implements SanPhamService {

    private SanPhamRepository sanPhamRepository = new SanPhamRepository();
    
    @Override
    public List<SanPhamRespone> getAll() {
        return sanPhamRepository.getAll();
    }
 }
```

> SanPhamController.java

```
@WebServlet(name = "Servlet", value = {"/san-pham/hien-thi", "/san-pham/add-update", "/san-pham/delete", "/san-pham/detail"})
public class SanPhamController extends HttpServlet {

    private SanPhamService sanPhamService = new SanPhamSerViceImpl();
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("hien-thi")) {
            this.hienThiSanPham(request, response);
        } else if (uri.contains("detail")) {
       //     this.detailSanPham(request, response);
        } else {
          //  this.deleteSanPham(request, response);
        }
    }
    
      private void hienThiSanPham(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<SanPhamRespone> sanPhamRespones = sanPhamService.getAll();
        request.setAttribute("sanPhamRespones", sanPhamRespones);
        request.getRequestDispatcher("/view/san-pham.jsp").forward(request, response);
    }
}
```


> san-pham.jsp

chú ý bạn cần import <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> trên đầu file 
 <c:forEach items="${sanPhamRespones}" var="sanPham"> item: thông tin lặp , var: Tên của biến để hiển thị trạng thái vòng lặp	

![image](https://user-images.githubusercontent.com/109157942/224486688-a56e7c47-2dca-4a66-bd1b-689eff694a48.png)


## Thêm mới sản phẩm

> SanPhamRequest.java

```
 private String ten;
    private String gia;

    public SanPhamRequest(String ten, String gia) {
        this.ten = ten;
        this.gia = gia;
    }

    public SanPhamRequest() {
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }
```

> SanPhamRepository.java

```
 public boolean add(SanPham sanPham){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()){
            transaction = session.beginTransaction();
            session.persist(sanPham);
            // cách 2
            // session.save(sanPham);
            // cách 3
            // session.saveOrUpdate(sanPham);
            transaction.commit();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
```

> bạn có đọc thêm về một vài thuộc tính persist, save, merge, saveOrUpdate [tậi đây](https://viblo.asia/p/hibernate-save-persist-update-merge-saveorupdate-RQqKLz1Nl7z)


> SanPhamService.java

```
    HashMap<String, String> add(SanPhamRequest request) ;
```

> SanPhamSerViceImpl.java

```
 @Override
    public HashMap<String, String> add(SanPhamRequest request) {
        HashMap<String, String> lists = new HashMap<>();
        // Check validate
        if (StringUtils.isEmpty(request.getTen())) {
            lists.put("GIA_EMPTY", "Vui lòng nhập giá");
        }
        if (StringUtils.isEmpty(request.getGia())) {
            lists.put("TEN_EMPTY", "Tên sản Phẩm không được để trống");
        }
        // Khi thoả mãn validate
        if (lists.isEmpty()) {
            SanPham sanPham = new SanPham();
            sanPham.setTen(request.getTen());
            sanPham.setGia(Float.parseFloat(request.getGia()));
            sanPhamRepository.add(sanPham);
        }

        return  lists;
    }
```

> SanPhamController.java

```
 @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("add-update")) {
            this.addOrUpdateLopHoc(request, response);
        }
    }
    // url: http://localhost8080/sanPham/add-update?id= &ten=ao1&gia=12
    //?id= &ten=ao1&gia=12 là các request parameter: id, ten, gia
     private void addOrUpdateLopHoc(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String ten = request.getParameter("ten");
        String gia = request.getParameter("gia");

        SanPhamRequest sanPhamRequest = new SanPhamRequest();
        sanPhamRequest.setTen(ten);
        sanPhamRequest.setGia(gia);
            // hiển thị các lỗi trên form 
            HashMap<String, String> errors = sanPhamService.add(sanPhamRequest);
            request.setAttribute("errors", errors);
            // hiển thị lại data lên trên table 
            List<SanPhamRespone> sanPhamRespones = sanPhamService.getAll();
            request.setAttribute("sanPhamRespones", sanPhamRespones);
            
            request.getRequestDispatcher("/view/san-pham.jsp").forward(request, response);
       

    }
```


> san-pham.jsp

lưu ý trong input phải có tên của  request trùng với thuộc tính trong SanPhamRequest 
 // <input type="text" name="ten" />

![image](https://user-images.githubusercontent.com/109157942/224486733-1c7a3e79-1229-4b12-903b-8bdb341a6f6c.png)


## Sửa sản phẩm 



> SanPhamRepository.java

```
 public SanPham getOne(int id){
        SanPham sanPham = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()){
            transaction = session.beginTransaction();
            sanPham = session.get(SanPham.class, id);
            transaction.commit();
        } catch (Exception e) {
        }
        return sanPham;
    }
   
    public SanPhamRespone findById(int id){
        SanPhamRespone sanPham = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()){
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT new com.example.demo.response.SanPhamRespone(sp.id, sp.ten, sp.gia) FROM SanPham sp WHERE sp.id = :id", SanPhamRespone.class);
            query.setParameter("id", id);
            sanPham = (SanPhamRespone) query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
        }
        return sanPham;
    }
    
    public Boolean update(SanPham sanPham) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.merge(sanPham);
            // cách 2
            // session.saveOrUpdate(sanPham);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }
```

> SanPhamService.java

```
SanPhamRespone findById(int id);

    HashMap<String, String> update(SanPhamRequest request, int id);
```

> SanPhamSerViceImpl.java

```
  @Override
    public SanPhamRespone findById(int id) {
        return sanPhamRepository.findById(id);
    }

    @Override
    public HashMap<String, String> update(SanPhamRequest request, int id) {
        HashMap<String, String> lists = new HashMap<>();
        SanPham updateSanPham = sanPhamRepository.getOne(id);
        // Check validate
        if (StringUtils.isEmpty(request.getTen())) {
            lists.put("GIA_EMPTY", "Vui lòng nhập giá");
        }
        if (StringUtils.isEmpty(request.getGia())) {
            lists.put("TEN_EMPTY", "Tên sản Phẩm không được để trống");
        }
        if(updateSanPham == null){
            lists.put("San_PHAM_EXIST", "Id không tồn tại");
        }
        // Khi thoả mãn validate
        if (lists.isEmpty()) {
            updateSanPham.setTen(request.getTen());
            updateSanPham.setGia(Float.parseFloat(request.getGia()));
            sanPhamRepository.update(updateSanPham);
        }

        return  lists;
    }
```

> SanPhamController.java

```
 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("hien-thi")) {
            this.hienThiSanPham(request, response);
        } else if (uri.contains("detail")) {
            this.detailSanPham(request, response);
        } else {
            this.deleteSanPham(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("add-update")) {
            this.addOrUpdateLopHoc(request, response);
        }
    }
    // hiển thị chi tiết sản phẩm cần update 
    private void detailSanPham(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        SanPhamRespone sanPhamRespone = sanPhamService.findById(Integer.parseInt(id));
        List<SanPhamRespone> sanPhamRespones = sanPhamService.getAll();
        request.setAttribute("sanPhamRespone", sanPhamRespone);
        request.setAttribute("sanPhamRespones", sanPhamRespones);
        request.getRequestDispatcher("/view/san-pham.jsp").forward(request, response);
    }
    // update hoặc lưu 
    private void addOrUpdateLopHoc(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        String ten = request.getParameter("ten");
        String gia = request.getParameter("gia");

        SanPhamRequest sanPhamRequest = new SanPhamRequest();
        sanPhamRequest.setTen(ten);
        sanPhamRequest.setGia(gia);
        // nếu id = rỗng thì sẽ thực hiện lưu sản phẩm và ngược lại 
        if(id.isEmpty()){
            HashMap<String, String> errors = sanPhamService.add(sanPhamRequest);
            request.setAttribute("errors", errors);
            List<SanPhamRespone> sanPhamRespones = sanPhamService.getAll();
            request.setAttribute("sanPhamRespones", sanPhamRespones);
            request.getRequestDispatcher("/view/san-pham.jsp").forward(request, response);
        }else{
            HashMap<String, String> errors = sanPhamService.update(sanPhamRequest, Integer.parseInt(id));
            request.setAttribute("errors", errors);
            List<SanPhamRespone> sanPhamRespones = sanPhamService.getAll();
            request.setAttribute("sanPhamRespones", sanPhamRespones);
            request.getRequestDispatcher("/view/san-pham.jsp").forward(request, response);
        }

    }
```


## delete sản phẩm 

> SanPhamRepository.java 

```
 public boolean delete(SanPham sanPham){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()){
            transaction = session.beginTransaction();
            session.delete(sanPham);
            transaction.commit();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
```

> SanPhamService.java 

```
    boolean delete(int id);
```

>  SanPhamSerViceImpl.java

```
@Override
    public boolean delete(int id) {
       SanPham sanPham = sanPhamRepository.getOne(id);
       return sanPhamRepository.delete(sanPham);
    }
```

> SanPhamController.java

```
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("hien-thi")) {
            this.hienThiSanPham(request, response);
        } else if (uri.contains("detail")) {
            this.detailSanPham(request, response);
        } else {
            this.deleteSanPham(request, response);
        }
    }
    private void deleteSanPham(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        sanPhamService.delete(Integer.parseInt(id));
        List<SanPhamRespone> sanPhamRespones = sanPhamService.getAll();
        request.setAttribute("sanPhamRespones", sanPhamRespones);
        request.getRequestDispatcher("/view/san-pham.jsp").forward(request, response);
    }
```

> toàn bộ code trong bài viết có thể lấy [tại đây](https://github.com/thangdtph27626/demo_hibernat_servlet.github.io)
