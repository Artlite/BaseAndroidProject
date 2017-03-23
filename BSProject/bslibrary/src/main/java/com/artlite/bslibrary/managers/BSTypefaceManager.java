package com.artlite.bslibrary.managers;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.artlite.bslibrary.helpers.log.BSLogHelper;

/**
 * Class which provide the functional for {@link Typeface}
 */

public final class BSTypefaceManager extends BSBaseManager {

    //==============================================================================================
    //                                      CONSTANTS
    //==============================================================================================

    /**
     * Fonts constants
     */
    private static final String K_FONT_FOLDER = "fonts";

    /**
     * Delimiter value
     */
    private static final String K_DELIMITER = "/";

    /**
     * Aller
     */
    private static final String K_FONT_ALLER = getFontPath("Aller.ttf");
    private static final String K_FONT_ALLER_BOLD = getFontPath("Aller_Bold.ttf");
    private static final String K_FONT_ALLER_ITALIC = getFontPath("Aller_Italic.ttf");
    private static final String K_FONT_ALLER_BOLD_ITALIC = getFontPath("Aller_Bold_Italic.ttf");

    /**
     * Roboto
     */
    private static final String K_FONT_ROBOTO = getFontPath("Roboto.ttf");
    private static final String K_FONT_ROBOTO_BOLD = getFontPath("Roboto_Bold.ttf");
    private static final String K_FONT_ROBOTO_ITALIC = getFontPath("Roboto_Italic.ttf");
    private static final String K_FONT_ROBOTO_BOLD_ITALIC = getFontPath("Roboto_Bold_Italic.ttf");

    /**
     * OpenSans
     */
    private static final String K_FONT_OS = getFontPath("OpenSans.ttf");
    private static final String K_FONT_OS_BOLD = getFontPath("OpenSans_Bold.ttf");
    private static final String K_FONT_OS_ITALIC = getFontPath("OpenSans_Italic.ttf");
    private static final String K_FONT_OS_BOLD_ITALIC = getFontPath("OpenSans_Bold_Italic.ttf");

    /**
     * San Francisco
     */
    private static final String K_FONT_SF = getFontPath("SF.otf");
    private static final String K_FONT_SF_BOLD = getFontPath("SF_Bold.otf");
    private static final String K_FONT_SF_ITALIC = getFontPath("SF_Italic.otf");
    private static final String K_FONT_SF_BOLD_ITALIC = getFontPath("SF_Bold_Italic.otf");

    /**
     * Ubuntu
     */
    private static final String K_FONT_U = getFontPath("Ubuntu.ttf");
    private static final String K_FONT_U_BOLD = getFontPath("Ubuntu_Bold.ttf");
    private static final String K_FONT_U_ITALIC = getFontPath("Ubuntu_Italic.ttf");
    private static final String K_FONT_U_BOLD_ITALIC = getFontPath("Ubuntu_Bold_Italic.ttf");

    /**
     * Ubuntu Mono
     */
    private static final String K_FONT_UM = getFontPath("UbuntuMono.ttf");
    private static final String K_FONT_UM_BOLD = getFontPath("UbuntuMono_Bold.ttf");
    private static final String K_FONT_UM_ITALIC = getFontPath("UbuntuMono_Italic.ttf");
    private static final String K_FONT_UM_BOLD_ITALIC = getFontPath("UbuntuMono_Bold_Italic.ttf");

    /**
     * Bariol
     */
    private static final String K_FONT_BARIOL = getFontPath("Bariol.otf");
    private static final String K_FONT_BARIOL_BOLD = getFontPath("Bariol_Bold.otf");
    private static final String K_FONT_BARIOL_ITALIC = getFontPath("Bariol_Italic.otf");
    private static final String K_FONT_BARIOL_BOLD_ITALIC = getFontPath("Bariol_Bold_Italic.otf");

    //==============================================================================================
    //                                      INSTANCE
    //==============================================================================================

    /**
     * Instance of {@link BSTypefaceManager}
     */
    private static BSTypefaceManager instance;

    //==============================================================================================
    //                                      FIELDS
    //==============================================================================================

    /**
     * Aller
     */
    private Typeface ALLER;
    private Typeface ALLER_BOLD;
    private Typeface ALLER_ITALIC;
    private Typeface ALLER_BOLD_ITALIC;

    /**
     * Roboto
     */
    private Typeface ROBOTO;
    private Typeface ROBOTO_BOLD;
    private Typeface ROBOTO_ITALIC;
    private Typeface ROBOTO_BOLD_ITALIC;

    /**
     * Open Sans
     */
    private Typeface OS;
    private Typeface OS_BOLD;
    private Typeface OS_ITALIC;
    private Typeface OS_BOLD_ITALIC;

    /**
     * San Francisco
     */
    private Typeface SF;
    private Typeface SF_BOLD;
    private Typeface SF_ITALIC;
    private Typeface SF_BOLD_ITALIC;

    /**
     * Ubuntu
     */
    private Typeface U;
    private Typeface U_BOLD;
    private Typeface U_ITALIC;
    private Typeface U_BOLD_ITALIC;

    /**
     * Ubuntu Mono
     */
    private Typeface UM;
    private Typeface UM_BOLD;
    private Typeface UM_ITALIC;
    private Typeface UM_BOLD_ITALIC;

    /**
     * Bariol
     */
    private Typeface BARIOL;
    private Typeface BARIOL_BOLD;
    private Typeface BARIOL_ITALIC;
    private Typeface BARIOL_BOLD_ITALIC;

    //==============================================================================================
    //                                   STATIC METHODS
    //==============================================================================================

    /**
     * Method which provide the initialization of {@link BSEventManager}
     *
     * @param context instance of {@link Context}
     * @return initialization result
     * @warning should be initializing in application singleton
     */
    public static void init(@Nullable final Context context) {
        if (isNull(instance)) {
            instance = new BSTypefaceManager(context);
        } else {
            Log.e(TAG, "TypeFace manager is already created");
        }
    }

    /**
     * Method which provide the instance of {@link BSEventManager}
     *
     * @return instance of {@link BSEventManager}
     */
    @Nullable
    protected static BSTypefaceManager getInstance() {
        if (isNull(instance)) {
            Log.e(TAG, "TypeFace manager should be initialized the Application singleton");
        }
        return instance;
    }

    /**
     * Method which provide the getting of the font path
     *
     * @param fontName font name
     * @return path for font
     */
    protected static String getFontPath(@NonNull String fontName) {
        return K_FONT_FOLDER + K_DELIMITER + fontName;
    }

    /**
     * Method which provide the getting of the {@link Typeface} from
     *
     * @param context  instance of {@link Context}
     * @param fontPath font path
     * @return instance of {@link Typeface}
     */
    @Nullable
    protected static Typeface getTypeface(@NonNull Context context,
                                          @NonNull String fontPath) {
        final String methodName = " Typeface getTypeface(context, fontPath)";
        try {
            return Typeface.createFromAsset(context.getAssets(), fontPath);
        } catch (Exception ex) {
            BSLogHelper.log(null, methodName, ex, null);
        }
        return null;
    }

    //==============================================================================================
    //                                      CONSTRUCTOR
    //==============================================================================================

    /**
     * Default constructor
     *
     * @param context
     */
    public BSTypefaceManager(@NonNull Context context) {
        super(context);
        final String methodName = "BSTypefaceManager(context)";
        try {
            //Aller
            ALLER = getTypeface(context, K_FONT_ALLER);
            ALLER_BOLD = getTypeface(context, K_FONT_ALLER_BOLD);
            ALLER_ITALIC = getTypeface(context, K_FONT_ALLER_ITALIC);
            ALLER_BOLD_ITALIC = getTypeface(context, K_FONT_ALLER_BOLD_ITALIC);
            //Roboto
            ROBOTO = getTypeface(context, K_FONT_ROBOTO);
            ROBOTO_BOLD = getTypeface(context, K_FONT_ROBOTO_BOLD);
            ROBOTO_ITALIC = getTypeface(context, K_FONT_ROBOTO_ITALIC);
            ROBOTO_BOLD_ITALIC = getTypeface(context, K_FONT_ROBOTO_BOLD_ITALIC);
            //Open Sans
            OS = getTypeface(context, K_FONT_OS);
            OS_BOLD = getTypeface(context, K_FONT_OS_BOLD);
            OS_ITALIC = getTypeface(context, K_FONT_OS_ITALIC);
            OS_BOLD_ITALIC = getTypeface(context, K_FONT_OS_BOLD_ITALIC);
            //San Francisco
            SF = getTypeface(context, K_FONT_SF);
            SF_BOLD = getTypeface(context, K_FONT_SF_BOLD);
            SF_ITALIC = getTypeface(context, K_FONT_SF_ITALIC);
            SF_BOLD_ITALIC = getTypeface(context, K_FONT_SF_BOLD_ITALIC);
            //Ubuntu
            U = getTypeface(context, K_FONT_U);
            U_BOLD = getTypeface(context, K_FONT_U_BOLD);
            U_ITALIC = getTypeface(context, K_FONT_U_ITALIC);
            U_BOLD_ITALIC = getTypeface(context, K_FONT_U_BOLD_ITALIC);
            //Ubuntu Mono
            UM = getTypeface(context, K_FONT_UM);
            UM_BOLD = getTypeface(context, K_FONT_UM_BOLD);
            UM_ITALIC = getTypeface(context, K_FONT_UM_ITALIC);
            UM_BOLD_ITALIC = getTypeface(context, K_FONT_UM_BOLD_ITALIC);
            //Bariol
            BARIOL = getTypeface(context, K_FONT_BARIOL);
            BARIOL_BOLD = getTypeface(context, K_FONT_BARIOL_BOLD);
            BARIOL_ITALIC = getTypeface(context, K_FONT_BARIOL_ITALIC);
            BARIOL_BOLD_ITALIC = getTypeface(context, K_FONT_BARIOL_BOLD_ITALIC);
        } catch (Exception ex) {
            BSLogHelper.log(null, methodName, ex, null);
        }
    }

    //==============================================================================================
    //                                       GETTERS
    //==============================================================================================

    //==============================================================================================
    //                                       ALLER
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getAller() {
        return getInstance().ALLER;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getAllerBold() {
        return getInstance().ALLER_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getAllerItalic() {
        return getInstance().ALLER_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getAllerBoldItalic() {
        return getInstance().ALLER_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       ROBOTO
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getRoboto() {
        return getInstance().ROBOTO;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getRobotoBold() {
        return getInstance().ROBOTO_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getRobotoItalic() {
        return getInstance().ROBOTO_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getRobotoBoldItalic() {
        return getInstance().ROBOTO_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       OPEN SANS
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getOs() {
        return getInstance().OS;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getOsBold() {
        return getInstance().OS_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getOsItalic() {
        return getInstance().OS_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getOsBoldItalic() {
        return getInstance().OS_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       SAN FRANCISCO
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getSf() {
        return getInstance().SF;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getSfBold() {
        return getInstance().SF_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getSfItalic() {
        return getInstance().SF_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getSfBoldItalic() {
        return getInstance().SF_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       UBUNTU
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getUbuntu() {
        return getInstance().U;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getUbuntuBold() {
        return getInstance().U_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getUbuntuItalic() {
        return getInstance().U_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getUbuntuBoldItalic() {
        return getInstance().U_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       UBUNTU MONO
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getUbuntuMono() {
        return getInstance().UM;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getUbuntuMonoBold() {
        return getInstance().UM_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getUbuntuMonoItalic() {
        return getInstance().UM_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getUbuntuMonoBoldItalic() {
        return getInstance().UM_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       BARIOL
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getBariol() {
        return getInstance().BARIOL;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getBariolBold() {
        return getInstance().BARIOL_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getBariolItalic() {
        return getInstance().BARIOL_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getBariolBoldItalic() {
        return getInstance().BARIOL_BOLD_ITALIC;
    }

}
