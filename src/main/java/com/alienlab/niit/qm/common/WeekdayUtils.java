package com.alienlab.niit.qm.common;

/**
 * Created by Master QB on 2017/5/1.
 */
public class WeekdayUtils {

    public String convert(String daystring){
        String firstString = daystring.substring(1,3);
        String finall="";
        String weekString = firstString.substring(0,1);
        String sectionString = firstString.substring(1,2);
        if (weekString.equals("1")){
            if (sectionString.equals("1")){
                finall="周一:1-2节";
            }else if (sectionString.equals("2")){
                finall="周一:3-4节";

            }else if (sectionString.equals("3")){
                finall="周一:5-6节";

            }else if (sectionString.equals("4")){
                finall="周一:7-8节";

            }else if (sectionString.equals("5")){
                finall="周一:9-10节";

            }
        }else if (weekString.equals("2")){
            if (sectionString.equals("1")){
                finall="周二:1-2节";
            }else if (sectionString.equals("2")){
                finall="周二:3-4节";

            }else if (sectionString.equals("3")){
                finall="周二:5-6节";

            }else if (sectionString.equals("4")){
                finall="周二:7-8节";

            }else if (sectionString.equals("5")){
                finall="周二:9-10节";

            }

        }else if (weekString.equals("3")){
            if (sectionString.equals("1")){
                finall="周三:1-2节";
            }else if (sectionString.equals("2")){
                finall="周三:3-4节";

            }else if (sectionString.equals("3")){
                finall="周三:5-6节";

            }else if (sectionString.equals("4")){
                finall="周三:7-8节";

            }else if (sectionString.equals("5")){
                finall="周三:9-10节";

            }

        }else if (weekString.equals("4")){
            if (sectionString.equals("1")){
                finall="周四:1-2节";
            }else if (sectionString.equals("2")){
                finall="周四:3-4节";

            }else if (sectionString.equals("3")){
                finall="周四:5-6节";

            }else if (sectionString.equals("4")){
                finall="周四:7-8节";

            }else if (sectionString.equals("5")){
                finall="周四:9-10节";

            }

        }else if (weekString.equals("5")){
            if (sectionString.equals("1")){
                finall="周五:1-2节";
            }else if (sectionString.equals("2")){
                finall="周五:3-4节";

            }else if (sectionString.equals("3")){
                finall="周五:5-6节";

            }else if (sectionString.equals("4")){
                finall="周五:7-8节";

            }else if (sectionString.equals("5")){
                finall="周五:9-10节";

            }

        }else if (weekString.equals("6")){
            if (sectionString.equals("1")){
                finall="周六:1-2节";
            }else if (sectionString.equals("2")){
                finall="周六:3-4节";

            }else if (sectionString.equals("3")){
                finall="周六:5-6节";

            }else if (sectionString.equals("4")){
                finall="周六:7-8节";

            }else if (sectionString.equals("5")){
                finall="周六:9-10节";

            }

        }else if (weekString.equals("7")){
            if (sectionString.equals("1")){
                finall="周日:1-2节";
            }else if (sectionString.equals("2")){
                finall="周日:3-4节";

            }else if (sectionString.equals("3")){
                finall="周日:5-6节";

            }else if (sectionString.equals("4")){
                finall="周日:7-8节";

            }else if (sectionString.equals("5")){
                finall="周日:9-10节";

            }

        }else if (weekString.equals("9")){
            finall="时间:不定节";
        }


        return finall;

    }



}
