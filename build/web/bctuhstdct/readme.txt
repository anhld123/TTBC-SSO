Chú ý:
Bảng cấu hình module báo cáo: MASTER_MODULE_RPT_FAST
Trường quan trọng ID, mô tả module

Bảng cấu hình module MASTER_TABLE_RPT_FAST
  MTRF_TABLE_ID    : ID của bảng thứ tự tăng dần ko trùng nhau
  MTRF_MODULE_ID   : Module báo cáo nhanh id này phải là 1 trong các id của bảng module ở trên
  MTRF_TABLE_NAME  : Bảng dữ liệu cần lấy dữ liệu
  MTRF_TABLE_DESC  : Mô tả bảng dữ liệu
  MTRF_TABLE_FLAG  : Cờ trạng thái Y sẽ được dùng, N Sẽ không được dùng

Bảng cấu hình các trường dữ liệu MASTER_COLUMN_RPT_FAST
  MCRF_TABLE_ID         : ID bảng dữ liệu là ID của bảng bên trên
  MCRF_TABLE_NAME       : Bảng dữ liệu cần lấy trường
  MCRF_COLUMN_NAME      : Tên cột dữ liệu sẽ lấy lên báo cáo
  MCRF_COLUMN_DESC      : Mô tả của cột dữ liệu Trường mô tả này sẽ hiển thị lên báo cáo nên cần mô tả chính xác
  MCRF_COLUMN_FLAG      : Cờ trạng thái Y sẽ được dùng, N Sẽ không được dùng
  MCRF_WHERE            : Lấy điều kiện cho pos_cd
  MCRF_DATA_TYPE        : Kiểu dữ liệu của trường
  MCRF_DATA_LENGTH      : Độ dài của trường dữ liệu (Không quan trọng lắm)
  MCRF_COLUMN_ID        : Thứ tự của trường
  MCRF_MODULE_ID        : Thuộc module nào có thể 1 bảng thuộc nhiều module khác nhau
  MCRF_DATE_WHERE_FLAG  : Cờ trạng thái Y sẽ được dùng, N Sẽ không được dùng

Bảng cấu hình join các bảng trong cùng một module:

MASTER_JOIN_TABLE_RPT_FAST
  MJTRF_TABLE_NAME  : Bảng dữ liệu quan hệ
  MJTRF_DESC        : Mô tả bảng dữ liệu
  MJTRF_COLUMN_NAME : Trường dữ liệu join của bảng 
  MJTRF_GROUP_ID    : Nhóm thiết lập (Chú ý trường này rất quan trọng 2 bảng quan hệ với nhau phải cùng nhóm)
  MJTRF_MODULE_ID   : Thuộc module nào

Ví Dụ: JOIN giữa 2 bảng HSKU, HSKH

HSKU	Mã khách hàng	KU_MAKH	1	\01\
HSKH	Mã khách hàng	KH_MAKH	1	\01\

Ngoài ra còn các bảng lưu các báo cáo

SAVE_TITLE_RPT_FAST:        Lưu tiêu đề của báo cáo, thuộc module nào, phần điều kiện thêm ngoài
SAVE_COLUMN_RPT_FAST:       Lưu cột dữ liệu của báo cáo cùng thứ tự của nó trên báo cáo theo người dùng sắp xếp
SAVE_COLUMN_WHERE_RPT_FAST: Lưu điều kiện lấy lên báo cáo  