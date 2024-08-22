package mz.sga.ujc.demo.service.auth;

import org.springframework.stereotype.Service;

@Service
public class HtmlEmailText {
    public String EmailText(String codigo){
        return  "<!DOCTYPE html>    \n" +
                "<html>    \n" +
                "<head>\n" +
                "<title>Push Email</title>\n" +
                "<link rel=\"shortcut icon\" href=\"favicon.ico\">\n" +
                "<style type=\"text/css\">\n" +
                "table[name=\"blk_permission\"], table[name=\"blk_footer\"] {display:none;} \n" +
                "</style>\n" +
                "<meta name=\"googlebot\" content=\"noindex\" />\n" +
                "<META NAME=\"ROBOTS\" CONTENT=\"NOINDEX, NOFOLLOW\"/><link rel=\"stylesheet\" href=\"/style/dhtmlwindow.css\" type=\"text/css\" />\n" +
                "<script type=\"text/javascript\" src=\"/script/dhtmlwindow.js\">\n" +
                "/***********************************************\n" +
                "* DHTML Window Widget- © Dynamic Drive (www.dynamicdrive.com)\n" +
                "* This notice must stay intact for legal use.\n" +
                "* Visit www.dynamicdrive.com for full source code\n" +
                "***********************************************/\n" +
                "</script>\n" +
                "<link rel=\"stylesheet\" href=\"/style/modal.css\" type=\"text/css\" />\n" +
                "<script type=\"text/javascript\" src=\"/script/modal.js\"></script>\n" +
                "<script type=\"text/javascript\">\n" +
                "\tfunction show_popup(popup_name,popup_url,popup_title,width,height) {var widthpx = width +  \"px\";var heightpx = height +  \"px\";emailwindow = dhtmlmodal.open(popup_name , 'iframe', popup_url , popup_title , 'width=' + widthpx + ',height='+ heightpx + ',center=1,resize=0,scrolling=1');}\n" +
                " function show_modal(popup_name,popup_url,popup_title,width,height){var widthpx = width +  \"px\";var heightpx = height +  \"px\";emailwindow = dhtmlmodal.open(popup_name , 'iframe', popup_url , popup_title , 'width=' + widthpx + ',height='+ heightpx + ',modal=1,center=1,resize=0,scrolling=1');}\n" +
                "var popUpWin=0;\n" +
                "\tfunction popUpWindow(URLStr,PopUpName, width, height){if(popUpWin) { if(!popUpWin.closed) popUpWin.close();}var left = (screen.width - width) / 2;var top = (screen.height - height) / 2;popUpWin = open(URLStr, PopUpName,\t'toolbar=0,location=0,directories=0,status=0,menub\tar=0,scrollbar=0,resizable=0,copyhistory=yes,width='+width+',height='+height+',left='+left+', \ttop='+top+',screenX='+left+',screenY='+top+'');}\n" +
                "</script>\n" +
                "<meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\">    \n" +
                "<style type=\"text/css\">    \n" +
                "/*** BMEMBF Start ***/    \n" +
                "[name=bmeMainBody]{min-height:1000px;}    \n" +
                "@media only screen and (max-width: 480px){table.blk, table.tblText, .bmeHolder, .bmeHolder1, table.bmeMainColumn{width:100% !important;} }        \n" +
                "@media only screen and (max-width: 480px){.bmeImageCard table.bmeCaptionTable td.tblCell{padding:0px 20px 20px 20px !important;} }        \n" +
                "@media only screen and (max-width: 480px){.bmeImageCard table.bmeCaptionTable.bmeCaptionTableMobileTop td.tblCell{padding:20px 20px 0 20px !important;} }        \n" +
                "@media only screen and (max-width: 480px){table.bmeCaptionTable td.tblCell{padding:10px !important;} }        \n" +
                "@media only screen and (max-width: 480px){table.tblGtr{ padding-bottom:20px !important;} }        \n" +
                "@media only screen and (max-width: 480px){td.blk_container, .blk_parent, .bmeLeftColumn, .bmeRightColumn, .bmeColumn1, .bmeColumn2, .bmeColumn3, .bmeBody{display:table !important;max-width:600px !important;width:100% !important;} }        \n" +
                "@media only screen and (max-width: 480px){table.container-table, .bmeheadertext, .container-table { width: 95% !important; } }        \n" +
                "@media only screen and (max-width: 480px){.mobile-footer, .mobile-footer a{ font-size: 13px !important; line-height: 18px !important; } .mobile-footer{ text-align: center !important; } table.share-tbl { padding-bottom: 15px; width: 100% !important; } table.share-tbl td { display: block !important; text-align: center !important; width: 100% !important; } }        \n" +
                "@media only screen and (max-width: 480px){td.bmeShareTD, td.bmeSocialTD{width: 100% !important; } }        \n" +
                "@media only screen and (max-width: 480px){td.tdBoxedTextBorder{width: auto !important;}}    \n" +
                "@media only screen and (max-width: 480px){table.blk, table[name=tblText], .bmeHolder, .bmeHolder1, table[name=bmeMainColumn]{width:100% !important;} }    \n" +
                "@media only screen and (max-width: 480px){.bmeImageCard table.bmeCaptionTable td[name=tblCell]{padding:0px 20px 20px 20px !important;} }    \n" +
                "@media only screen and (max-width: 480px){.bmeImageCard table.bmeCaptionTable.bmeCaptionTableMobileTop td[name=tblCell]{padding:20px 20px 0 20px !important;} }    \n" +
                "@media only screen and (max-width: 480px){table.bmeCaptionTable td[name=tblCell]{padding:10px !important;} }    \n" +
                "@media only screen and (max-width: 480px){table[name=tblGtr]{ padding-bottom:20px !important;} }    \n" +
                "@media only screen and (max-width: 480px){td.blk_container, .blk_parent, [name=bmeLeftColumn], [name=bmeRightColumn], [name=bmeColumn1], [name=bmeColumn2], [name=bmeColumn3], [name=bmeBody]{display:table !important;max-width:600px !important;width:100% !important;} }    \n" +
                "@media only screen and (max-width: 480px){table[class=container-table], .bmeheadertext, .container-table { width: 95% !important; } }    \n" +
                "@media only screen and (max-width: 480px){.mobile-footer, .mobile-footer a{ font-size: 13px !important; line-height: 18px !important; } .mobile-footer{ text-align: center !important; } table[class=\"share-tbl\"] { padding-bottom: 15px; width: 100% !important; } table[class=\"share-tbl\"] td { display: block !important; text-align: center !important; width: 100% !important; } }    \n" +
                "@media only screen and (max-width: 480px){td[name=bmeShareTD], td[name=bmeSocialTD]{width: 100% !important; } }    \n" +
                "@media only screen and (max-width: 480px){td[name=tdBoxedTextBorder]{width: auto !important;}}    \n" +
                "@media only screen and (max-width: 480px){.bmeImageCard table.bmeImageTable{height: auto !important; width:100% !important; padding:20px !important;clear:both; float:left !important; border-collapse: separate;} }    \n" +
                "@media only screen and (max-width: 480px){.bmeMblInline table.bmeImageTable{height: auto !important; width:100% !important; padding:10px !important;clear:both;} }    \n" +
                "@media only screen and (max-width: 480px){.bmeMblInline table.bmeCaptionTable{width:100% !important; clear:both;} }    \n" +
                "@media only screen and (max-width: 480px){table.bmeImageTable{height: auto !important; width:100% !important; padding:10px !important;clear:both; } }    \n" +
                "@media only screen and (max-width: 480px){table.bmeCaptionTable{width:100% !important;  clear:both;} }    \n" +
                "@media only screen and (max-width: 480px){table.bmeImageContainer{width:100% !important; clear:both; float:left !important;} }    \n" +
                "@media only screen and (max-width: 480px){table.bmeImageTable td{padding:0px !important; height: auto; } }    \n" +
                "@media only screen and (max-width: 480px){td.bmeImageContainerRow{padding:0px !important;}}    \n" +
                "@media only screen and (max-width: 480px){img.mobile-img-large{width:100% !important; height:auto !important;} }    \n" +
                "@media only screen and (max-width: 480px){img.bmeRSSImage{max-width:320px; height:auto !important;}}    \n" +
                "@media only screen and (min-width: 640px){img.bmeRSSImage{max-width:600px !important; height:auto !important;} }    \n" +
                "@media only screen and (max-width: 480px){.trMargin img{height:10px;} }    \n" +
                "@media only screen and (max-width: 480px){div.bmefooter, div.bmeheader{ display:block !important;} }    \n" +
                "@media only screen and (max-width: 480px){.tdPart{ width:100% !important; clear:both; float:left !important; } }    \n" +
                "@media only screen and (max-width: 480px){table.blk_parent1, table.tblPart {width: 100% !important; } }    \n" +
                "@media only screen and (max-width: 480px){.tblLine{min-width: 100% !important;}}     \n" +
                "@media only screen and (max-width: 480px){.bmeMblCenter img { margin: 0 auto; } }       \n" +
                "@media only screen and (max-width: 480px){.bmeMblCenter, .bmeMblCenter div, .bmeMblCenter span  { text-align: center !important; text-align: -webkit-center !important; } }    \n" +
                "@media only screen and (max-width: 480px){.bmeNoBr br, .bmeImageGutterRow, .bmeMblStackCenter .bmeShareItem .tdMblHide { display: none !important; } }    \n" +
                "@media only screen and (max-width: 480px){.bmeMblInline table.bmeImageTable, .bmeMblInline table.bmeCaptionTable, td.bmeMblInline { clear: none !important; width:50% !important; } }    \n" +
                "@media only screen and (max-width: 480px){.bmeMblInlineHide, .bmeShareItem .trMargin { display: none !important; } }    \n" +
                "@media only screen and (max-width: 480px){.bmeMblInline table.bmeImageTable img, .bmeMblShareCenter.tblContainer.mblSocialContain, .bmeMblFollowCenter.tblContainer.mblSocialContain{width: 100% !important; } }    \n" +
                "@media only screen and (max-width: 480px){.bmeMblStack> .bmeShareItem{width: 100% !important; clear: both !important;} }    \n" +
                "@media only screen and (max-width: 480px){.bmeShareItem{padding-top: 10px !important;} }    \n" +
                "@media only screen and (max-width: 480px){.tdPart.bmeMblStackCenter, .bmeMblStackCenter .bmeFollowItemIcon {padding:0px !important; text-align: center !important;} }    \n" +
                "@media only screen and (max-width: 480px){.bmeMblStackCenter> .bmeShareItem{width: 100% !important;} }    \n" +
                "@media only screen and (max-width: 480px){ td.bmeMblCenter {border: 0 none transparent !important;}}    \n" +
                "@media only screen and (max-width: 480px){.bmeLinkTable.tdPart td{padding-left:0px !important; padding-right:0px !important; border:0px none transparent !important;padding-bottom:15px !important;height: auto !important;}}    \n" +
                "@media only screen and (max-width: 480px){.tdMblHide{width:10px !important;} }    \n" +
                "@media only screen and (max-width: 480px){.bmeShareItemBtn{display:table !important;}}    \n" +
                "@media only screen and (max-width: 480px){.bmeMblStack td {text-align: left !important;}}    \n" +
                "@media only screen and (max-width: 480px){.bmeMblStack .bmeFollowItem{clear:both !important; padding-top: 10px !important;}}    \n" +
                "@media only screen and (max-width: 480px){.bmeMblStackCenter .bmeFollowItemText{padding-left: 5px !important;}}    \n" +
                "@media only screen and (max-width: 480px){.bmeMblStackCenter .bmeFollowItem{clear:both !important;align-self:center; float:none !important; padding-top:10px;margin: 0 auto;}}    \n" +
                "@media only screen and (max-width: 480px){    \n" +
                ".tdPart> table{width:100% !important;}    \n" +
                "}    \n" +
                "@media only screen and (max-width: 480px){.tdPart>table.bmeLinkContainer{ width:auto !important; }}    \n" +
                "@media only screen and (max-width: 480px){.tdPart.mblStackCenter>table.bmeLinkContainer{ width:100% !important;}}     \n" +
                ".blk_parent:first-child, .blk_parent{float:left;}    \n" +
                ".blk_parent:last-child{float:right;}    \n" +
                "/*** BMEMBF END ***/    \n" +
                "table[name=\"bmeMainBody\"], body {background-color:#ffffff;}    \n" +
                " td[name=\"bmePreHeader\"] {background-color:#ffffff;}    \n" +
                " td[name=\"bmeHeader\"] {background:#ffffff;background-color:#f5f2d0;}    \n" +
                " td[name=\"bmeBody\"], table[name=\"bmeBody\"] {background-color:#ffffff;}    \n" +
                " td[name=\"bmePreFooter\"] {background-color:#ffffff;}    \n" +
                " td[name=\"bmeFooter\"] {background-color:#ffffff;}    \n" +
                " td[name=\"tblCell\"], .blk {font-family:initial;font-weight:normal;font-size:initial;}    \n" +
                " table[name=\"blk_blank\"] td[name=\"tblCell\"] {font-family:Arial, Helvetica, sans-serif;font-size:14px;}    \n" +
                " [name=bmeMainColumnParent] {border-color:transparent;border-width:0px;border-style:none;border-radius:0px;border-collapse:separate;border-spacing:0px;overflow:visible;}    \n" +
                " [name=bmeMainContentParent] {border-color:transparent;border-width:0px;border-style:none;border-radius:0px;overflow:hidden;}    \n" +
                " [name=bmeMainContent] {border-color:transparent;border-width:0px;border-style:none;border-radius:0px;border-collapse:separate;border-spacing:0px;overflow:visible;}    \n" +
                "</style>    \n" +
                "</head>    \n" +
                "<body marginheight=0 marginwidth=0 topmargin=0 leftmargin=0 style=\"height: 100% !important; margin: 0; padding: 0; width: 100% !important;min-width: 100%;\">    \n" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" name=\"bmeMainBody\" style=\"background-color: rgb(255, 255, 255);\" bgcolor=\"#ffffff\"><tbody><tr><td width=\"100%\" valign=\"top\" align=\"center\">    \n" +
                "<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" name=\"bmeMainColumnParentTable\" width=\"100%\"><tbody><tr><td name=\"bmeMainColumnParent\" style=\"border: 0px none transparent; border-radius: 0px; border-collapse: separate; border-spacing: 0px; overflow: visible;\">     \n" +
                "<table name=\"bmeMainColumn\" class=\"bmeMainColumn\" style=\"max-width: 100%; overflow: visible;\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\" width=\"100%\">    <tbody><tr><td width=\"100%\" class=\"blk_container bmeHolder\" name=\"bmePreHeader\" valign=\"top\" align=\"center\" style=\"color: rgb(102, 102, 102); border: 0px none transparent; background-color: rgb(255, 255, 255);\" bgcolor=\"#ffffff\"></td></tr> <tr><td width=\"100%\" class=\"bmeHolder\" valign=\"top\" align=\"center\" name=\"bmeMainContentParent\" style=\"border: 0px none transparent; border-radius: 0px; border-collapse: separate; border-spacing: 0px; overflow: hidden;\">     \n" +
                "<table name=\"bmeMainContent\" style=\"border-radius: 0px; border-collapse: separate; border-spacing: 0px; border: 0px none transparent; overflow: visible;\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\"> <tbody><tr><td width=\"100%\" class=\"blk_container bmeHolder\" name=\"bmeHeader\" valign=\"top\" align=\"center\" style=\"border: 0px none transparent; background-color: rgb(245, 242, 208);\" bgcolor=\"#f5f2d0\"><div id=\"dv_9\" class=\"blk_wrapper\">    \n" +
                "<table width=\"600\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"blk\" name=\"blk_divider\" ><tbody><tr><td class=\"tblCellMain\" style=\"padding: 10px 20px;\">    \n" +
                "<table class=\"tblLine\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"border-top-width: 0px; border-top-style: none; min-width: 1px;\"><tbody><tr><td><span></span></td></tr></tbody>    \n" +
                "</table></td></tr></tbody>    \n" +
                "</table></div><div id=\"dv_1\" class=\"blk_wrapper\">    \n" +
                "<table width=\"600\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"blk\" name=\"blk_image\"><tbody><tr><td>    \n" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\"><tbody><tr><td align=\"center\" class=\"bmeImage\" style=\"border-collapse: collapse; padding: 10px 0px; font-size: 30px; color: #0a83f5;\"><b>Universidade Joaquim Chissano</b></td></tr></tbody>    \n" +
                "</table></td></tr></tbody>    \n" +
                "</table></div><div id=\"dv_11\" class=\"blk_wrapper\">    \n" +
                "<table width=\"600\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"blk\" name=\"blk_divider\"><tbody><tr><td class=\"tblCellMain\" style=\"padding: 20px;\">    \n" +
                "<table class=\"tblLine\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"border-top-width: 0px; border-top-style: none; min-width: 1px;\"><tbody><tr><td><span></span></td></tr></tbody>    \n" +
                "</table></td></tr></tbody>    \n" +
                "</table></div>\n" +
                "</td></tr> <tr><td width=\"100%\" class=\"blk_container bmeHolder bmeBody\" name=\"bmeBody\" valign=\"top\" align=\"center\" style=\"color: rgb(56, 56, 56); border: 0px none transparent; background-color: rgb(255, 255, 255);\" bgcolor=\"#ffffff\"><div id=\"dv_13\" class=\"blk_wrapper\">    \n" +
                "<table width=\"600\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"blk\" name=\"blk_divider\"><tbody><tr><td class=\"tblCellMain\" style=\"padding: 15px 20px;\">    \n" +
                "<table class=\"tblLine\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"border-top-width: 0px; border-top-style: none; min-width: 1px;\"><tbody><tr><td><span></span></td></tr></tbody>    \n" +
                "</table></td></tr></tbody>    \n" +
                "</table></div><div id=\"dv_12\" class=\"blk_wrapper\">    \n" +
                "<table width=\"600\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"blk\" name=\"blk_button\" ><tbody><tr><td width=\"20\"></td><td align=\"center\">    \n" +
                "<table class=\"tblContainer\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\"><tbody><tr><td height=\"20\"></td></tr><tr><td align=\"center\">    \n" +
                "<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"bmeButton\" align=\"center\" style=\"border-collapse: separate;\"><tbody><tr><td style=\"border-radius: 0px; border: 1px solid rgb(69, 153, 232); text-align: center; font-family: Arial, Helvetica, sans-serif; font-size: 14px; padding: 15px 40px; font-weight: normal; border-collapse: separate;\" class=\"bmeButtonText\"><span style=\"font-family: 'Arial Narrow', 'Arial MT Condensed Light', sans-serif; font-size: 24px; color: rgb(236, 127, 232);\">    \n" +
                "<a style=\"color: rgb(127, 200, 236); text-decoration: none;\" target=\"_blank\">O seu codigo é:"+codigo+"</a></span></td></tr></tbody>    \n" +
                "</table></td></tr><tr><td height=\"20\"></td></tr></tbody>    \n" +
                "</table></td><td width=\"20\"></td></tr></tbody>    \n" +
                "</table></div><div id=\"dv_14\" class=\"blk_wrapper\">    \n" +
                "<table width=\"600\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"blk\" name=\"blk_text\"><tbody><tr><td>    \n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\" class=\"bmeContainerRow\"><tbody><tr><td class=\"tdPart\" valign=\"top\" align=\"center\">    \n" +
                "<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"600\" name=\"tblText\" style=\"float:left; background-color:transparent;\" align=\"left\" class=\"tblText\"><tbody><tr><td valign=\"top\" align=\"left\" name=\"tblCell\" style=\"padding: 10px 20px; font-family: Arial, Helvetica, sans-serif; font-size: 14px; font-weight: 400; color: rgb(56, 56, 56); text-align: left;\" class=\"tblCell\"><div style=\"line-height: 200%; text-align: center;\"><span style=\"font-size: 14px; font-family: Arial, Helvetica, sans-serif; color: #000000; line-height: 200%;\">\n" +
                "    Use o codígo para fazer login, inserindo o codigo e a senha por si definida.\n" +
                "</table></td></tr></tbody>    \n" +
                "</table></td></tr></tbody>    \n" +
                "</table></div>\n" +
                "<div id=\"dv_6\" class=\"blk_wrapper\">    \n" +
                "<table class=\"blk\" name=\"blk_button\" width=\"600\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><td width=\"20\"></td><td align=\"center\">    \n" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"tblContainer\"><tbody><tr><td height=\"20\"></td></tr><tr><td align=\"center\">    \n" +
                "<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\" class=\"bmeButton\" style=\"border-collapse: separate;\"><tbody><tr><td class=\"bmeButtonText\" style=\"border-radius: 0px; border: 0px none transparent; text-align: center; padding: 20px 40px; font-weight: normal; font-family: Arial, Helvetica, sans-serif; font-size: 14px; background-color: rgb(69, 153, 232);\"><span style=\"font-family: 'Arial Narrow', 'Arial MT Condensed Light', sans-serif; font-size: 20px; color: rgb(255, 255, 255);\">    \n" +
                "<a target=\"_blank\" style=\"color:#FFFFFF;text-decoration:none;\">View More</a></span></td></tr></tbody>    \n" +
                "</table></td></tr><tr><td height=\"20\"></td></tr></tbody>    \n" +
                "</table></td><td width=\"20\"></td></tr></tbody>    \n" +
                "</table></div>\n" +
                "</td></tr> <tr><td width=\"100%\" class=\"blk_container bmeHolder\" name=\"bmePreFooter\" valign=\"top\" align=\"center\" style=\"border: 0px none transparent; background-color: rgb(255, 255, 255);\" bgcolor=\"#ffffff\">\n" +
                "<div id=\"dv_4\" class=\"blk_wrapper\">    \n" +
                "<table width=\"600\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"blk\" name=\"blk_divider\" ><tbody><tr><td class=\"tblCellMain\" style=\"padding: 30px 20px;\">    \n" +
                "<table class=\"tblLine\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"border-top-width: 0px; border-top-style: none; min-width: 1px;\"><tbody><tr><td><span></span></td></tr></tbody>    \n" +
                "</table></td></tr></tbody>    \n" +
                "</table></div><div id=\"dv_8\" class=\"blk_wrapper\">    \n" +
                "<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\"  name=\"blk_social_follow\" width=\"600\" class=\"blk\"><tbody><tr><td class=\"tblCellMain\" align=\"center\" style=\"padding-top:10px; padding-bottom:10px; padding-left:20px; padding-right:20px;\">    \n" +
                "<table class=\"tblContainer mblSocialContain\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\" style=\"text-align:center;\"><tbody><tr><td class=\"tdItemContainer\" >    \n" +
                "<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"mblSocialContain\" style=\"table-layout: auto;\"><tbody><tr><td valign=\"top\" name=\"bmeSocialTD\" class=\"bmeSocialTD\"><!--[if gte mso 6]></td><td align=\"left\" valign=\"top\"><![endif]-->    \n" +
                "<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"bmeFollowItem\" type=\"facebook\" style=\"float: left; display: block;\" align=\"left\"><tbody><tr><td align=\"left\" class=\"bmeFollowItemIcon\" gutter=\"10\" width=\"24\" style=\"padding-right:10px;height:20px;\">    \n" +
                "<a href=\"https://www.facebook.com/ujc.ac.mz\" target=_blank  style=\"display: inline-block;background-color: rgb(53, 91, 161);border-radius: 4px;border-style: none; border-width: 0px; border-color: rgba(0, 0, 0, 0);\" target=\"_blank\"><img    \n" +
                " src=\"https://ui.benchmarkemail.com/images/editor/socialicons/fb_btn.png\" alt=\"Facebook\" style=\"display: block; max-width: 114px;\" border=\"0\" width=\"24\" height=\"24\"></a></td></tr></tbody>    \n" +
                "</table><!--[if gte mso 6]></td><td align=\"left\" valign=\"top\"><![endif]-->    \n" +
                "<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"bmeFollowItem\" type=\"instagram\" style=\"float: left; display: block;\" align=\"left\"><tbody><tr><td align=\"left\" class=\"bmeFollowItemIcon\" gutter=\"10\" width=\"24\" style=\"padding-right:10px;height:20px;\">    \n" +
                "<a href=\"https://www.instagram.com/universidadejoaquimchissano/?igsh=MngwOHluYnFlaWh1\" target=_blank  style=\"display: inline-block;background-color: rgb(82, 127, 166);border-radius: 4px;border-style: none; border-width: 0px; border-color: rgb(0, 0, 238);\" target=\"_blank\"><img    \n" +
                " src=\"https://ui.benchmarkemail.com/images/editor/socialicons/in_btn.png\" alt=\"Instagram\" style=\"display: block; max-width: 114px;\" border=\"0\" width=\"24\" height=\"24\"></a></td></tr></tbody>    \n" +
                "</table><!--[if gte mso 6]></td><td align=\"left\" valign=\"top\"><![endif]-->    \n" +
                "</td></tr></tbody>    \n" +
                "</table></td></tr></tbody>    \n" +
                "</table></td></tr></tbody>    \n" +
                "</table></div>\n" +
                "</td></tr> </tbody>    \n" +
                "</table> </td></tr>\n" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" name=\"bmeMainBody\" style=\"background-color: rgb(255, 255, 255);\" bgcolor=\"#ffffff\"><tbody><tr><td width=\"100%\" valign=\"top\" align=\"center\">    \n" +
                "<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" name=\"bmeMainColumnParentTable\" width=\"100%\"><tbody><tr><td name=\"bmeMainColumnParent\" style=\"border: 0px none transparent; border-radius: 0px; border-collapse: separate; border-spacing: 0px; overflow: visible;\">     \n" +
                "<table name=\"bmeMainColumn\" class=\"bmeMainColumn\" style=\"max-width: 100%; overflow: visible;\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\" width=\"100%\">    <tbody><tr><td width=\"100%\" class=\"blk_container bmeHolder\" name=\"bmePreHeader\" valign=\"top\" align=\"center\" style=\"color: rgb(102, 102, 102); border: 0px none transparent; background-color: rgb(255, 255, 255);\" bgcolor=\"#ffffff\"></td></tr> <tr><td width=\"100%\" class=\"bmeHolder\" valign=\"top\" align=\"center\" name=\"bmeMainContentParent\" style=\"border: 0px none transparent; border-radius: 0px; border-collapse: separate; border-spacing: 0px; overflow: hidden;\">     \n" +
                "<table name=\"bmeMainContent\" style=\"border-radius: 0px; border-collapse: separate; border-spacing: 0px; border: 0px none transparent; overflow: visible;\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\"> \n" +
                "<tbody><tr><td width=\"100%\" class=\"blk_container bmeHolder\" name=\"bmeHeader\" valign=\"top\" align=\"center\" style=\"border: 0px none transparent; background-color: rgb(245, 242, 208);\" bgcolor=\"#f5f2d0\">\n" +
                "<div id=\"dv_1\" class=\"blk_wrapper\">    \n" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\"><tbody><tr><td align=\"center\" class=\"bmeImage\" style=\"border-collapse: collapse; padding: 10px 0px; font-size: 15px; color: #0a83f5;\">Campus do Zimpeto, Rua do Grande Maputo, Q.88, Bairro de Zimpeto, Cidadede  Maputo</td></tr></tbody>    \n" +
                "</table>\n" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\"><tbody><tr><td align=\"center\" class=\"bmeImage\" style=\"border-collapse: collapse; padding: 10px 0px; font-size: 15px; color: #0a83f5;\">&copy; UJC. Todos direitos reservados.</td></tr></tbody>    \n" +
                "</table></td></tr></tbody>    \n" +
                "</table></div>\n" +
                "</tbody>    \n" +
                "</table></td></tr></tbody>    \n" +
                "</table></td></tr></tbody>    \n" +
                "</table>    \n" +
                "</body>    \n" +
                "</html>";
    }
}
