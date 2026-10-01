package vbsp.ims.sso;

import java.util.*;

public class MenuBuildingService {

    public void extractUserPermissions(List<SsoMenuResponseDto.RoleItemDto> roles, List<String> userAllowedResourceKeys, List<String> actionList) {
        if (roles != null) {
            for (SsoMenuResponseDto.RoleItemDto roleItem : roles) {
                extractPermissionsForSingleRole(roleItem, userAllowedResourceKeys, actionList);
            }
        }
    }

    public void extractPermissionsForSingleRole(SsoMenuResponseDto.RoleItemDto roleItem, List<String> userAllowedResourceKeys, List<String> actionList) {
        if (roleItem != null && roleItem.getResources() != null) {
            for (Map.Entry<String, SsoMenuResponseDto.ResourceDetailDto> entry : roleItem.getResources().entrySet()) {
                String resourceKey = entry.getKey();
                if (!userAllowedResourceKeys.contains(resourceKey)) {
                    userAllowedResourceKeys.add(resourceKey);
                }

                SsoMenuResponseDto.ResourceDetailDto detail = entry.getValue();
                if (detail != null && detail.getActions() != null) {
                    actionList.addAll(detail.getActions());
                }
            }
        }
    }

    public Map<String, MenuDataDto> createDbMenuMap(List<MenuDataDto> lstMenu) {
        Map<String, MenuDataDto> map = new HashMap<>();
        if (lstMenu != null) {
            for (MenuDataDto dto : lstMenu) {
                if (dto.getMenuId() != null) {
                    String idStr = dto.getMenuId().toString();
                    // Lưu key gốc từ DB (VD: "147")
                    map.put(idStr, dto);

                    if (!idStr.startsWith("TTBC_")) {
                        map.put("TTBC_" + idStr, dto);
                    } else {
                        long numeric = getNumericId(idStr);
                        if (numeric != -1) {
                            map.put(String.valueOf(numeric), dto);
                        }
                    }
                }
            }
        }
        return map;
    }

    public Map<MenuDataDto, Map<MenuDataDto, List<MenuDataDto>>> buildMenuTree3Levels(
            List<String> allowedMenus,
            List<String> userAllowedResourceKeys,
            SsoMenuResponseDto.RoleItemDto chosenRoleObj,
            Map<String, MenuDataDto> dbMenuMap,
            List<MenuDataDto> lstMenu,
            List<String> menuList) {

        Map<MenuDataDto, Map<MenuDataDto, List<MenuDataDto>>> menuTree3Levels = new LinkedHashMap<>();

        for (String parentKey : allowedMenus) {
            if (userAllowedResourceKeys.contains(parentKey)) {
                long parentNumericId = getNumericId(parentKey);

                // 1. Xử lý Menu Cha (Cấp 1)
                MenuDataDto parentDto = dbMenuMap.get(parentKey);
                if (parentDto == null) {
                    parentDto = dbMenuMap.get(String.valueOf(parentNumericId));
                }

                if (parentDto == null) {
                    parentDto = new MenuDataDto();
                    parentDto.setText(parentKey);
                }

                // Ép buộc menuId của cha thành số thuần túy
                if (parentNumericId != -1) {
                    parentDto.setMenuId(String.valueOf(parentNumericId));
                } else if (parentDto.getMenuId() != null) {
                    long idNum = getNumericId(parentDto.getMenuId().toString());
                    parentDto.setMenuId(idNum != -1 ? String.valueOf(idNum) : parentDto.getMenuId());
                }

                Map<MenuDataDto, List<MenuDataDto>> level1Map = new LinkedHashMap<>();
                List<String> rawActions = extractRawActionsForParent(chosenRoleObj, parentKey);

                // Sắp xếp các action con tăng dần theo giá trị số
                Collections.sort(rawActions, new Comparator<String>() {
                    @Override
                    public int compare(String s1, String s2) {
                        try {
                            int num1 = Integer.parseInt(s1.substring(s1.lastIndexOf("_") + 1));
                            int num2 = Integer.parseInt(s2.substring(s2.lastIndexOf("_") + 1));
                            return Integer.compare(num1, num2);
                        } catch (Exception e) {
                            return s1.compareTo(s2);
                        }
                    }
                });

                // 2. Xử lý Menu Con Cấp 2
                for (String actId : rawActions) {
                    long l1NumericId = getNumericId(actId);

                    MenuDataDto level1Dto = dbMenuMap.get(actId);
                    if (level1Dto == null) {
                        level1Dto = dbMenuMap.get(String.valueOf(l1NumericId));
                    }

                    if (level1Dto == null) {
                        level1Dto = new MenuDataDto();
                        level1Dto.setText(actId);
                    }

                    // Ép buộc menuId của cấp 2 thành số thuần túy
                    if (l1NumericId != -1) {
                        level1Dto.setMenuId(String.valueOf(l1NumericId));
                    } else if (level1Dto.getMenuId() != null) {
                        long idNum = getNumericId(level1Dto.getMenuId().toString());
                        level1Dto.setMenuId(idNum != -1 ? String.valueOf(idNum) : level1Dto.getMenuId());
                    }

                    // 3. Xử lý Menu Cấp 3 (SubItem từ DB)
                    List<MenuDataDto> level2List = new ArrayList<>();
                    for (MenuDataDto subItem : lstMenu) {
                        if (subItem.getParentId() != null) {
                            try {
                                long subParentId = Long.parseLong(subItem.getParentId().toString());
                                if (subParentId == l1NumericId) {
                                    // Làm sạch cả menuId của subItem nếu cần
                                    if (subItem.getMenuId() != null) {
                                        long subNumId = getNumericId(subItem.getMenuId().toString());
                                        if (subNumId != -1) {
                                            subItem.setMenuId(String.valueOf(subNumId));
                                        }
                                    }
                                    if (!level2List.contains(subItem)) {
                                        level2List.add(subItem);
                                    }
                                }
                            } catch (Exception ignored) {
                            }
                        }
                    }
                    level1Map.put(level1Dto, level2List);
                }

                menuTree3Levels.put(parentDto, level1Map);

                // Đảm bảo menuList chỉ chứa ID số thuần túy
                menuList.add(parentNumericId != -1 ? String.valueOf(parentNumericId) : parentKey);
            }
        }
        return menuTree3Levels;
    }

    public List<String> extractRawActionsForParent(SsoMenuResponseDto.RoleItemDto roleItem, String parentKey) {
        List<String> rawActions = new ArrayList<>();
        if (roleItem != null && roleItem.getResources() != null && roleItem.getResources().containsKey(parentKey)) {
            SsoMenuResponseDto.ResourceDetailDto detail = roleItem.getResources().get(parentKey);
            if (detail != null && detail.getActions() != null) {
                for (String act : detail.getActions()) {
                    if (!rawActions.contains(act)) {
                        rawActions.add(act);
                    }
                }
            }
        }
        return rawActions;
    }

    /**
     * Trích xuất số ID an toàn từ chuỗi menuId
     */
    public long getNumericId(String menuId) {
        try {
            if (menuId != null && menuId.contains("_")) {
                return Long.parseLong(menuId.substring(menuId.lastIndexOf("_") + 1));
            }
            return Long.parseLong(menuId);
        } catch (Exception e) {
            return -1;
        }
    }
}
