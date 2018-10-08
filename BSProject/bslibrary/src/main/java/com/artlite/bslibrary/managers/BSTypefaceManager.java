package com.artlite.bslibrary.managers;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.artlite.bslibrary.constants.BSTypeface;
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
    private static final String K_FONT_ALLER
            = getFontPath("Aller.ttf");
    private static final String K_FONT_ALLER_BOLD
            = getFontPath("Aller_Bold.ttf");
    private static final String K_FONT_ALLER_ITALIC
            = getFontPath("Aller_Italic.ttf");
    private static final String K_FONT_ALLER_BOLD_ITALIC
            = getFontPath("Aller_Bold_Italic.ttf");

    /**
     * Roboto
     */
    private static final String K_FONT_ROBOTO
            = getFontPath("Roboto.ttf");
    private static final String K_FONT_ROBOTO_BOLD
            = getFontPath("Roboto_Bold.ttf");
    private static final String K_FONT_ROBOTO_ITALIC
            = getFontPath("Roboto_Italic.ttf");
    private static final String K_FONT_ROBOTO_BOLD_ITALIC
            = getFontPath("Roboto_Bold_Italic.ttf");

    /**
     * OpenSans
     */
    private static final String K_FONT_OS
            = getFontPath("OpenSans.ttf");
    private static final String K_FONT_OS_BOLD
            = getFontPath("OpenSans_Bold.ttf");
    private static final String K_FONT_OS_ITALIC
            = getFontPath("OpenSans_Italic.ttf");
    private static final String K_FONT_OS_BOLD_ITALIC
            = getFontPath("OpenSans_Bold_Italic.ttf");

    /**
     * San Francisco
     */
    private static final String K_FONT_SF
            = getFontPath("SF.otf");
    private static final String K_FONT_SF_BOLD
            = getFontPath("SF_Bold.otf");
    private static final String K_FONT_SF_ITALIC
            = getFontPath("SF_Italic.otf");
    private static final String K_FONT_SF_BOLD_ITALIC
            = getFontPath("SF_Bold_Italic.otf");

    /**
     * Ubuntu
     */
    private static final String K_FONT_U
            = getFontPath("Ubuntu.ttf");
    private static final String K_FONT_U_BOLD
            = getFontPath("Ubuntu_Bold.ttf");
    private static final String K_FONT_U_ITALIC
            = getFontPath("Ubuntu_Italic.ttf");
    private static final String K_FONT_U_BOLD_ITALIC
            = getFontPath("Ubuntu_Bold_Italic.ttf");

    /**
     * Ubuntu Mono
     */
    private static final String K_FONT_UM
            = getFontPath("UbuntuMono.ttf");
    private static final String K_FONT_UM_BOLD
            = getFontPath("UbuntuMono_Bold.ttf");
    private static final String K_FONT_UM_ITALIC
            = getFontPath("UbuntuMono_Italic.ttf");
    private static final String K_FONT_UM_BOLD_ITALIC
            = getFontPath("UbuntuMono_Bold_Italic.ttf");

    /**
     * Bariol
     */
    private static final String K_FONT_BARIOL
            = getFontPath("Bariol.otf");
    private static final String K_FONT_BARIOL_BOLD
            = getFontPath("Bariol_Bold.otf");
    private static final String K_FONT_BARIOL_ITALIC
            = getFontPath("Bariol_Italic.otf");
    private static final String K_FONT_BARIOL_BOLD_ITALIC
            = getFontPath("Bariol_Bold_Italic.otf");

    /**
     * Comfortaa
     */
    private static final String K_FONT_COMFORTAA
            = getFontPath("Comfortaa.ttf");
    private static final String K_FONT_COMFORTAA_BOLD
            = getFontPath("Comfortaa_Bold.ttf");
    private static final String K_FONT_COMFORTAA_ITALIC
            = getFontPath("Comfortaa.ttf");
    private static final String K_FONT_COMFORTAA_BOLD_ITALIC
            = getFontPath("Comfortaa_Bold.ttf");

    /**
     * Varela Round
     */
    private static final String K_FONT_VR
            = getFontPath("VarelaRound.ttf");
    private static final String K_FONT_VR_BOLD
            = getFontPath("VarelaRound.ttf");
    private static final String K_FONT_VR_ITALIC
            = getFontPath("VarelaRound.ttf");
    private static final String K_FONT_VR_BOLD_ITALIC
            = getFontPath("VarelaRound.ttf");

    /**
     * Montserat
     */
    private static final String K_FONT_MONTSERAT
            = getFontPath("Montserrat.otf");
    private static final String K_FONT_MONTSERAT_BOLD
            = getFontPath("Montserrat-Bold.otf");
    private static final String K_FONT_MONTSERAT_ITALIC
            = getFontPath("Montserrat_Italic.otf");
    private static final String K_FONT_MONTSERAT_BOLD_ITALIC
            = getFontPath("Montserrat-BoldItalic.otf");

    /**
     * Blogger Sans
     */
    private static final String K_FONT_BLOG_SANS
            = getFontPath("Blogger_Sans.otf");
    private static final String K_FONT_BLOG_SANS_BOLD
            = getFontPath("Blogger_Sans_Bold.otf");
    private static final String K_FONT_BLOG_SANS_ITALIC
            = getFontPath("Blogger_Sans_Italic.otf");
    private static final String K_FONT_BLOG_SANS_BOLD_ITALIC
            = getFontPath("Blogger_Sans_Bold_Italic.otf");

    /**
     * Clear Sans
     */
    private static final String K_FONT_CLEAR_SANS
            = getFontPath("ClearSans.ttf");
    private static final String K_FONT_CLEAR_SANS_BOLD
            = getFontPath("ClearSans_Bold.ttf");
    private static final String K_FONT_CLEAR_SANS_ITALIC
            = getFontPath("ClearSans_Italic.ttf");
    private static final String K_FONT_CLEAR_SANS_BOLD_ITALIC
            = getFontPath("ClearSans_BoldItalic.ttf");

    /**
     * Roboto Condensed
     */
    private static final String K_FONT_ROBOTO_CONSENDET
            = getFontPath("RobotoCondensed.ttf");
    private static final String K_FONT_ROBOTO_CONSENDET_BOLD
            = getFontPath("RobotoCondensed_Bold.ttf");
    private static final String K_FONT_ROBOTO_CONSENDET_ITALIC
            = getFontPath("RobotoCondensed_Italic.ttf");
    private static final String K_FONT_ROBOTO_CONSENDET_BOLD_ITALIC
            = getFontPath("RobotoCondensed_BoldItalic.ttf");

    /**
     * DejaVu Sans
     */
    private static final String K_FONT_DJV
            = getFontPath("DejaVuSans.ttf");
    private static final String K_FONT_DJV_BOLD
            = getFontPath("DejaVuSans_Bold.ttf");
    private static final String K_FONT_DJV_ITALIC
            = getFontPath("DejaVuSans_Oblique.ttf");
    private static final String K_FONT_DJV_BOLD_ITALIC
            = getFontPath("DejaVuSans_BoldOblique.ttf");

    /**
     * SFC
     */
    private static final String K_FONT_SFC
            = getFontPath("SFP.otf");
    private static final String K_FONT_SFC_BOLD
            = getFontPath("SFPB.otf");
    private static final String K_FONT_SFC_ITALIC
            = getFontPath("SFPI.otf");
    private static final String K_FONT_SFC_BOLD_ITALIC
            = getFontPath("SFPBI.otf");

    /**
     * GS
     */
    private static final String K_FONT_GS
            = getFontPath("GS.ttf");
    private static final String K_FONT_GS_BOLD
            = getFontPath("GS_Bold.ttf");
    private static final String K_FONT_GS_ITALIC
            = getFontPath("GS_Italic.ttf");
    private static final String K_FONT_GS_BOLD_ITALIC
            = getFontPath("GS_BoldItalic.ttf");

    /**
     * Fira
     */
    private static final String K_FONT_FIRA
            = getFontPath("FiraSans-Regular.otf");
    private static final String K_FONT_FIRA_BOLD
            = getFontPath("FiraSans-Bold.otf");
    private static final String K_FONT_FIRA_ITALIC
            = getFontPath("FiraSans-Italic.otf");
    private static final String K_FONT_FIRA_BOLD_ITALIC
            = getFontPath("FiraSans-BoldItalic.otf");

    /**
     * NUNITO
     */
    private static final String K_FONT_NUNITO
            = getFontPath("Nunito-Regular.ttf");
    private static final String K_FONT_NUNITO_BOLD
            = getFontPath("Nunito-Bold.ttf");
    private static final String K_FONT_NUNITO_ITALIC
            = getFontPath("Nunito-Regular.ttf");
    private static final String K_FONT_NUNITO_BOLD_ITALIC
            = getFontPath("Nunito-Bold.ttf");

    /**
     * FANTASQUE
     */
    private static final String K_FONT_FANTASQUE
            = getFontPath("fantasquesansmono-regular.otf");
    private static final String K_FONT_FANTASQUE_BOLD
            = getFontPath("fantasquesansmono-bold.otf");
    private static final String K_FONT_FANTASQUE_ITALIC
            = getFontPath("fantasquesansmono-italic.otf");
    private static final String K_FONT_FANTASQUE_BOLD_ITALIC
            = getFontPath("fantasquesansmono-bolditalic.otf");

    /**
     * LATO
     */
    private static final String K_FONT_LATO
            = getFontPath("Lato-Regular.ttf");
    private static final String K_FONT_LATO_BOLD
            = getFontPath("Lato-Bold.ttf");
    private static final String K_FONT_LATO_ITALIC
            = getFontPath("Lato-Italic.ttf");
    private static final String K_FONT_LATO_BOLD_ITALIC
            = getFontPath("Lato-BoldItalic.ttf");

    /**
     * PT_SANS
     */
    private static final String K_FONT_PT_SANS
            = getFontPath("PTS55F.ttf");
    private static final String K_FONT_PT_SANS_BOLD
            = getFontPath("PTS75F.ttf");
    private static final String K_FONT_PT_SANS_ITALIC
            = getFontPath("PTS56F.ttf");
    private static final String K_FONT_PT_SANS_BOLD_ITALIC
            = getFontPath("PTS76F.ttf");

    /**
     * MONTSERRAT_ALTERNATES
     */
    private static final String K_FONT_MONTSERRAT_ALTERNATES
            = getFontPath("MontserratAlternates-Regular.otf");
    private static final String K_FONT_MONTSERRAT_ALTERNATES_BOLD
            = getFontPath("MontserratAlternates-Bold.otf");
    private static final String K_FONT_MONTSERRAT_ALTERNATES_ITALIC
            = getFontPath("MontserratAlternates-Italic.otf");
    private static final String K_FONT_MONTSERRAT_ALTERNATES_BOLD_ITALIC
            = getFontPath("MontserratAlternates-BlackItalic.otf");

    /**
     * OS_CONDENSED
     */
    private static final String K_FONT_OS_CONDENSED
            = getFontPath("OpenSans-CondLight.ttf");
    private static final String K_FONT_OS_CONDENSED_BOLD
            = getFontPath("OpenSans-CondBold.ttf");
    private static final String K_FONT_OS_CONDENSED_ITALIC
            = getFontPath("OpenSans-CondLightItalic.ttf");
    private static final String K_FONT_OS_CONDENSED_BOLD_ITALIC
            = getFontPath("OpenSans-CondLightItalic.ttf");

    /**
     * RUBIK
     */
    private static final String K_FONT_RUBIK
            = getFontPath("Rubik-Regular.ttf");
    private static final String K_FONT_RUBIK_BOLD
            = getFontPath("Rubik-Bold.ttf");
    private static final String K_FONT_RUBIK_ITALIC
            = getFontPath("Rubik-Italic.ttf");
    private static final String K_FONT_RUBIK_BOLD_ITALIC
            = getFontPath("Rubik-BoldItalic.ttf");

    /**
     * PODKOVA
     */
    private static final String K_FONT_PODKOVA
            = getFontPath("Podkova-Regular.ttf");
    private static final String K_FONT_PODKOVA_BOLD
            = getFontPath("Podkova-Bold.ttf");
    private static final String K_FONT_PODKOVA_ITALIC
            = getFontPath("Podkova-Regular.ttf");
    private static final String K_FONT_PODKOVA_BOLD_ITALIC
            = getFontPath("Podkova-Bold.ttf");

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

    /**
     * Comfortaa
     */
    private Typeface COMFORTAA;
    private Typeface COMFORTAA_BOLD;
    private Typeface COMFORTAA_ITALIC;
    private Typeface COMFORTAA_BOLD_ITALIC;

    /**
     * Varela Round
     */
    private Typeface VR;
    private Typeface VR_BOLD;
    private Typeface VR_ITALIC;
    private Typeface VR_BOLD_ITALIC;

    /**
     * Montserat
     */
    private Typeface MONTSERAT;
    private Typeface MONTSERAT_BOLD;
    private Typeface MONTSERAT_ITALIC;
    private Typeface MONTSERAT_BOLD_ITALIC;

    /**
     * Blog Sans
     */
    private Typeface BLOG_SANS;
    private Typeface BLOG_SANS_BOLD;
    private Typeface BLOG_SANS_ITALIC;
    private Typeface BLOG_SANS_BOLD_ITALIC;

    /**
     * Clear Sans
     */
    private Typeface CLEAR_SANS;
    private Typeface CLEAR_SANS_BOLD;
    private Typeface CLEAR_SANS_ITALIC;
    private Typeface CLEAR_SANS_BOLD_ITALIC;

    /**
     * Roboto Condensed
     */
    private Typeface ROBOTO_CONSENDET;
    private Typeface ROBOTO_CONSENDET_BOLD;
    private Typeface ROBOTO_CONSENDET_ITALIC;
    private Typeface ROBOTO_CONSENDET_BOLD_ITALIC;

    /**
     * Roboto Condensed
     */
    private Typeface DJV;
    private Typeface DJV_BOLD;
    private Typeface DJV_ITALIC;
    private Typeface DJV_BOLD_ITALIC;

    /**
     * SFC
     */
    private Typeface SFC;
    private Typeface SFC_BOLD;
    private Typeface SFC_ITALIC;
    private Typeface SFC_BOLD_ITALIC;

    /**
     * GS
     */
    private Typeface GS;
    private Typeface GS_BOLD;
    private Typeface GS_ITALIC;
    private Typeface GS_BOLD_ITALIC;

    /**
     * FIRA
     */
    private Typeface FIRA;
    private Typeface FIRA_BOLD;
    private Typeface FIRA_ITALIC;
    private Typeface FIRA_BOLD_ITALIC;

    /**
     * FIRA
     */
    private Typeface NUNITO;
    private Typeface NUNITO_BOLD;
    private Typeface NUNITO_ITALIC;
    private Typeface NUNITO_BOLD_ITALIC;

    /**
     * FANTASQUE
     */
    private Typeface FANTASQUE;
    private Typeface FANTASQUE_BOLD;
    private Typeface FANTASQUE_ITALIC;
    private Typeface FANTASQUE_BOLD_ITALIC;

    /**
     * LATO
     */
    private Typeface LATO;
    private Typeface LATO_BOLD;
    private Typeface LATO_ITALIC;
    private Typeface LATO_BOLD_ITALIC;

    /**
     * PT_SANS
     */
    private Typeface PT_SANS;
    private Typeface PT_SANS_BOLD;
    private Typeface PT_SANS_ITALIC;
    private Typeface PT_SANS_BOLD_ITALIC;

    /**
     * MONTSERRAT_ALTERNATES
     */
    private Typeface MONTSERRAT_ALTERNATES;
    private Typeface MONTSERRAT_ALTERNATES_BOLD;
    private Typeface MONTSERRAT_ALTERNATES_ITALIC;
    private Typeface MONTSERRAT_ALTERNATES_BOLD_ITALIC;

    /**
     * OS_CONDENSED
     */
    private Typeface OS_CONDENSED;
    private Typeface OS_CONDENSED_BOLD;
    private Typeface OS_CONDENSED_ITALIC;
    private Typeface OS_CONDENSED_BOLD_ITALIC;

    /**
     * OS_CONDENSED
     */
    private Typeface RUBIK;
    private Typeface RUBIK_BOLD;
    private Typeface RUBIK_ITALIC;
    private Typeface RUBIK_BOLD_ITALIC;

    /**
     * PODKOVA
     */
    private Typeface PODKOVA;
    private Typeface PODKOVA_BOLD;
    private Typeface PODKOVA_ITALIC;
    private Typeface PODKOVA_BOLD_ITALIC;

    /**
     * Instance of the {@link BSTypeface}
     */
    private BSTypeface typeface = BSTypeface.OPEN_SANS;

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
    public static void init(@Nullable final Context context,
                            BSTypeface typeface) {
        if (isNull(instance)) {
            instance = new BSTypefaceManager(context);
            instance.typeface = typeface;
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
            //Comfortaa
            COMFORTAA = getTypeface(context, K_FONT_COMFORTAA);
            COMFORTAA_BOLD = getTypeface(context, K_FONT_COMFORTAA_BOLD);
            COMFORTAA_ITALIC = getTypeface(context, K_FONT_COMFORTAA_ITALIC);
            COMFORTAA_BOLD_ITALIC = getTypeface(context, K_FONT_COMFORTAA_BOLD_ITALIC);
            //Varela Round
            VR = getTypeface(context, K_FONT_VR);
            VR_BOLD = getTypeface(context, K_FONT_VR_BOLD);
            VR_ITALIC = getTypeface(context, K_FONT_VR_ITALIC);
            VR_BOLD_ITALIC = getTypeface(context, K_FONT_VR_BOLD_ITALIC);
            //Varela Round
            MONTSERAT = getTypeface(context, K_FONT_MONTSERAT);
            MONTSERAT_BOLD = getTypeface(context, K_FONT_MONTSERAT_BOLD);
            MONTSERAT_ITALIC = getTypeface(context, K_FONT_MONTSERAT_ITALIC);
            MONTSERAT_BOLD_ITALIC = getTypeface(context, K_FONT_MONTSERAT_BOLD_ITALIC);
            //Blogger Sans
            BLOG_SANS = getTypeface(context, K_FONT_BLOG_SANS);
            BLOG_SANS_BOLD = getTypeface(context, K_FONT_BLOG_SANS_BOLD);
            BLOG_SANS_ITALIC = getTypeface(context, K_FONT_BLOG_SANS_ITALIC);
            BLOG_SANS_BOLD_ITALIC = getTypeface(context, K_FONT_BLOG_SANS_BOLD_ITALIC);
            //Blogger Sans
            CLEAR_SANS = getTypeface(context, K_FONT_CLEAR_SANS);
            CLEAR_SANS_BOLD = getTypeface(context, K_FONT_CLEAR_SANS_BOLD);
            CLEAR_SANS_ITALIC = getTypeface(context, K_FONT_CLEAR_SANS_ITALIC);
            CLEAR_SANS_BOLD_ITALIC = getTypeface(context, K_FONT_CLEAR_SANS_BOLD_ITALIC);
            //Blogger Sans
            ROBOTO_CONSENDET = getTypeface(context, K_FONT_ROBOTO_CONSENDET);
            ROBOTO_CONSENDET_BOLD = getTypeface(context, K_FONT_ROBOTO_CONSENDET_BOLD);
            ROBOTO_CONSENDET_ITALIC = getTypeface(context, K_FONT_ROBOTO_CONSENDET_ITALIC);
            ROBOTO_CONSENDET_BOLD_ITALIC = getTypeface(context, K_FONT_ROBOTO_CONSENDET_BOLD_ITALIC);
            //Blogger Sans
            DJV = getTypeface(context, K_FONT_DJV);
            DJV_BOLD = getTypeface(context, K_FONT_DJV_BOLD);
            DJV_ITALIC = getTypeface(context, K_FONT_DJV_ITALIC);
            DJV_BOLD_ITALIC = getTypeface(context, K_FONT_DJV_BOLD_ITALIC);
            //SFC
            SFC = getTypeface(context, K_FONT_SFC);
            SFC_BOLD = getTypeface(context, K_FONT_SFC_BOLD);
            SFC_ITALIC = getTypeface(context, K_FONT_SFC_ITALIC);
            SFC_BOLD_ITALIC = getTypeface(context, K_FONT_SFC_BOLD_ITALIC);
            //GS
            GS = getTypeface(context, K_FONT_GS);
            GS_BOLD = getTypeface(context, K_FONT_GS_BOLD);
            GS_ITALIC = getTypeface(context, K_FONT_GS_ITALIC);
            GS_BOLD_ITALIC = getTypeface(context, K_FONT_GS_BOLD_ITALIC);
            //FIRA
            FIRA = getTypeface(context, K_FONT_FIRA);
            FIRA_BOLD = getTypeface(context, K_FONT_FIRA_BOLD);
            FIRA_ITALIC = getTypeface(context, K_FONT_FIRA_ITALIC);
            FIRA_BOLD_ITALIC = getTypeface(context, K_FONT_FIRA_BOLD_ITALIC);
            //NUNITO
            NUNITO = getTypeface(context, K_FONT_NUNITO);
            NUNITO_BOLD = getTypeface(context, K_FONT_NUNITO_BOLD);
            NUNITO_ITALIC = getTypeface(context, K_FONT_NUNITO_ITALIC);
            NUNITO_BOLD_ITALIC = getTypeface(context, K_FONT_NUNITO_BOLD_ITALIC);
            //FANTASQUE
            FANTASQUE = getTypeface(context, K_FONT_FANTASQUE);
            FANTASQUE_BOLD = getTypeface(context, K_FONT_FANTASQUE_BOLD);
            FANTASQUE_ITALIC = getTypeface(context, K_FONT_FANTASQUE_ITALIC);
            FANTASQUE_BOLD_ITALIC = getTypeface(context, K_FONT_FANTASQUE_BOLD_ITALIC);
            //LATO
            LATO = getTypeface(context, K_FONT_LATO);
            LATO_BOLD = getTypeface(context, K_FONT_LATO_BOLD);
            LATO_ITALIC = getTypeface(context, K_FONT_LATO_ITALIC);
            LATO_BOLD_ITALIC = getTypeface(context, K_FONT_LATO_BOLD_ITALIC);
            //PT_SANS
            PT_SANS = getTypeface(context, K_FONT_PT_SANS);
            PT_SANS_BOLD = getTypeface(context, K_FONT_PT_SANS_BOLD);
            PT_SANS_ITALIC = getTypeface(context, K_FONT_PT_SANS_ITALIC);
            PT_SANS_BOLD_ITALIC = getTypeface(context, K_FONT_PT_SANS_BOLD_ITALIC);
            //MONTSERRAT_ALTERNATES
            MONTSERRAT_ALTERNATES = getTypeface(context, K_FONT_MONTSERRAT_ALTERNATES);
            MONTSERRAT_ALTERNATES_BOLD = getTypeface(context, K_FONT_MONTSERRAT_ALTERNATES_BOLD);
            MONTSERRAT_ALTERNATES_ITALIC = getTypeface(context, K_FONT_MONTSERRAT_ALTERNATES_ITALIC);
            MONTSERRAT_ALTERNATES_BOLD_ITALIC = getTypeface(context, K_FONT_MONTSERRAT_ALTERNATES_BOLD_ITALIC);
            //OS_CONDENSED
            OS_CONDENSED = getTypeface(context, K_FONT_OS_CONDENSED);
            OS_CONDENSED_BOLD = getTypeface(context, K_FONT_OS_CONDENSED_BOLD);
            OS_CONDENSED_ITALIC = getTypeface(context, K_FONT_OS_CONDENSED_ITALIC);
            OS_CONDENSED_BOLD_ITALIC = getTypeface(context, K_FONT_OS_CONDENSED_BOLD_ITALIC);
            //RUBIK
            RUBIK = getTypeface(context, K_FONT_RUBIK);
            RUBIK_BOLD = getTypeface(context, K_FONT_RUBIK_BOLD);
            RUBIK_ITALIC = getTypeface(context, K_FONT_RUBIK_ITALIC);
            RUBIK_BOLD_ITALIC = getTypeface(context, K_FONT_RUBIK_BOLD_ITALIC);
            //PODKOVA
            PODKOVA = getTypeface(context, K_FONT_PODKOVA);
            PODKOVA_BOLD = getTypeface(context, K_FONT_PODKOVA_BOLD);
            PODKOVA_ITALIC = getTypeface(context, K_FONT_PODKOVA_ITALIC);
            PODKOVA_BOLD_ITALIC = getTypeface(context, K_FONT_PODKOVA_BOLD_ITALIC);
        } catch (Exception ex) {
            BSLogHelper.log(null, methodName, ex, null);
        }
    }

    //==============================================================================================
    //                                       GETTERS
    //==============================================================================================

    //==============================================================================================
    //                                       DEFAULT
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getDefault() {
        return getInstance().getDefaultInner();
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getDefaultBold() {
        return getInstance().getDefaultBoldInner();
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getDefaultItalic() {
        return getInstance().getDefaultItalicInner();
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getDefaultBoldItalic() {
        return getInstance().getDefaultBoldItalicInner();
    }

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    protected Typeface getDefaultInner() {
        switch (typeface) {
            case GS:
                return getGs();
            case SF:
                return getSf();
            case DJV:
                getDjv();
            case SFC:
                getSfc();
            case ALLER:
                getAller();
            case BARIOL:
                getBariol();
            case ROBOTO:
                getRoboto();
            case UBUNTU:
                getUbuntu();
            case BLOG_SANS:
                getBlogSans();
            case COMFORTAA:
                getComfortaa();
            case MONTSERAT:
                getMontserat();
            case OPEN_SANS:
                return getOs();
            case CLEAR_SANS:
                return getClearSans();
            case UBUNTU_MONO:
                return getUbuntuMono();
            case VARELA_ROUND:
                return getVR();
            case ROBOTO_CONSENDET:
                return getRobotoCondensed();
            case FIRA_SANS:
                return getFira();
            case NUNITO:
                return getNunito();
            case FANTASQUE:
                return getFantasque();
            case LATO:
                return getLato();
            case PT_SANS:
                return getPtSans();
            case MONTSERRAT_ALTERNATES:
                return getMontserratAlternates();
            case OS_CONDENSED:
                return getOsCondensed();
            case RUBIK:
                return getRubik();
            case PODKOVA:
                return getPodkova();
        }
        return getOs();
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    protected Typeface getDefaultBoldInner() {
        switch (typeface) {
            case GS:
                return getGsBold();
            case SF:
                return getSfBold();
            case DJV:
                getDjvBold();
            case SFC:
                getSfcBold();
            case ALLER:
                getAllerBold();
            case BARIOL:
                getBariolBold();
            case ROBOTO:
                getRobotoBold();
            case UBUNTU:
                getUbuntuBold();
            case BLOG_SANS:
                getBlogSansBold();
            case COMFORTAA:
                getComfortaaBold();
            case MONTSERAT:
                getMontseratBold();
            case OPEN_SANS:
                return getOsBold();
            case CLEAR_SANS:
                return getClearSansBold();
            case UBUNTU_MONO:
                return getUbuntuMonoBold();
            case VARELA_ROUND:
                return getVRBold();
            case ROBOTO_CONSENDET:
                return getRobotoCondensedBold();
            case FIRA_SANS:
                return getFiraBold();
            case NUNITO:
                return getNunitoBold();
            case FANTASQUE:
                return getFantasqueBold();
            case LATO:
                return getLatoBold();
            case PT_SANS:
                return getPtSansBold();
            case MONTSERRAT_ALTERNATES:
                return getMontserratAlternatesBold();
            case OS_CONDENSED:
                return getOsCondensedBold();
            case RUBIK:
                return getRubikBold();
            case PODKOVA:
                return getPodkovaBold();
        }
        return getOsBold();
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    protected Typeface getDefaultItalicInner() {
        switch (typeface) {
            case GS:
                return getGsItalic();
            case SF:
                return getSfItalic();
            case DJV:
                getDjvItalic();
            case SFC:
                getSfcItalic();
            case ALLER:
                getAllerItalic();
            case BARIOL:
                getBariolItalic();
            case ROBOTO:
                getRobotoItalic();
            case UBUNTU:
                getUbuntuItalic();
            case BLOG_SANS:
                getBlogSansItalic();
            case COMFORTAA:
                getComfortaaItalic();
            case MONTSERAT:
                getMontseratItalic();
            case OPEN_SANS:
                return getOsItalic();
            case CLEAR_SANS:
                return getClearSansItalic();
            case UBUNTU_MONO:
                return getUbuntuMonoItalic();
            case VARELA_ROUND:
                return getVRItalic();
            case ROBOTO_CONSENDET:
                return getRobotoCondensedItalic();
            case FIRA_SANS:
                return getFiraItalic();
            case NUNITO:
                return getNunitoItalic();
            case FANTASQUE:
                return getFantasqueItalic();
            case LATO:
                return getLatoItalic();
            case PT_SANS:
                return getPtSansItalic();
            case MONTSERRAT_ALTERNATES:
                return getMontserratAlternatesItalic();
            case OS_CONDENSED:
                return getOsCondensedItalic();
            case RUBIK:
                return getRubikItalic();
            case PODKOVA:
                return getPodkovaItalic();
        }
        return getOsItalic();
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    protected Typeface getDefaultBoldItalicInner() {
        switch (typeface) {
            case GS:
                return getGsBoldItalic();
            case SF:
                return getSfBoldItalic();
            case DJV:
                getDjvBoldItalic();
            case SFC:
                getSfcBoldItalic();
            case ALLER:
                getAllerBoldItalic();
            case BARIOL:
                getBariolBoldItalic();
            case ROBOTO:
                getRobotoBoldItalic();
            case UBUNTU:
                getUbuntuBoldItalic();
            case BLOG_SANS:
                getBlogSansBoldItalic();
            case COMFORTAA:
                getComfortaaBoldItalic();
            case MONTSERAT:
                getMontseratBoldItalic();
            case OPEN_SANS:
                return getOsBoldItalic();
            case CLEAR_SANS:
                return getClearSansBoldItalic();
            case UBUNTU_MONO:
                return getUbuntuMonoBoldItalic();
            case VARELA_ROUND:
                return getVRBoldItalic();
            case ROBOTO_CONSENDET:
                return getRobotoCondensedBoldItalic();
            case FIRA_SANS:
                return getFiraBoldItalic();
            case NUNITO:
                return getNunitoBoldItalic();
            case FANTASQUE:
                return getFantasqueBoldItalic();
            case LATO:
                return getLatoBoldItalic();
            case PT_SANS:
                return getPtSansBoldItalic();
            case MONTSERRAT_ALTERNATES:
                return getMontserratAlternatesBoldItalic();
            case OS_CONDENSED:
                return getOsCondensedBoldItalic();
            case RUBIK:
                return getRubikBoldItalic();
            case PODKOVA:
                return getPodkovaBoldItalic();
        }
        return getOsBoldItalic();
    }

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

    //==============================================================================================
    //                                       COMFORTAA
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getComfortaa() {
        return getInstance().COMFORTAA;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getComfortaaBold() {
        return getInstance().COMFORTAA_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getComfortaaItalic() {
        return getInstance().COMFORTAA_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getComfortaaBoldItalic() {
        return getInstance().COMFORTAA_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       COMFORTAA
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getVR() {
        return getInstance().VR;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getVRBold() {
        return getInstance().VR_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getVRItalic() {
        return getInstance().VR_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getVRBoldItalic() {
        return getInstance().VR_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       MONTSERAT
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getMontserat() {
        return getInstance().MONTSERAT;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getMontseratBold() {
        return getInstance().MONTSERAT_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getMontseratItalic() {
        return getInstance().MONTSERAT_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getMontseratBoldItalic() {
        return getInstance().MONTSERAT_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       BLOG_SANS
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getBlogSans() {
        return getInstance().BLOG_SANS;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getBlogSansBold() {
        return getInstance().BLOG_SANS_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getBlogSansItalic() {
        return getInstance().BLOG_SANS_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getBlogSansBoldItalic() {
        return getInstance().BLOG_SANS_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       CLEAR_SANS
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getClearSans() {
        return getInstance().CLEAR_SANS;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getClearSansBold() {
        return getInstance().CLEAR_SANS_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getClearSansItalic() {
        return getInstance().CLEAR_SANS_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getClearSansBoldItalic() {
        return getInstance().CLEAR_SANS_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       ROBOTO_CONSENDET
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getRobotoCondensed() {
        return getInstance().ROBOTO_CONSENDET;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getRobotoCondensedBold() {
        return getInstance().ROBOTO_CONSENDET_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getRobotoCondensedItalic() {
        return getInstance().ROBOTO_CONSENDET_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getRobotoCondensedBoldItalic() {
        return getInstance().ROBOTO_CONSENDET_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       DJV
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getDjv() {
        return getInstance().DJV;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getDjvBold() {
        return getInstance().DJV_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getDjvItalic() {
        return getInstance().DJV_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getDjvBoldItalic() {
        return getInstance().DJV_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       SFC
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getSfc() {
        return getInstance().SFC;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getSfcBold() {
        return getInstance().SFC_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getSfcItalic() {
        return getInstance().SFC_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getSfcBoldItalic() {
        return getInstance().SFC_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       GS
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getGs() {
        return getInstance().GS;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getGsBold() {
        return getInstance().GS_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getGsItalic() {
        return getInstance().GS_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getGsBoldItalic() {
        return getInstance().GS_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       FIRA
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getFira() {
        return getInstance().FIRA;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getFiraBold() {
        return getInstance().FIRA_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getFiraItalic() {
        return getInstance().FIRA_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getFiraBoldItalic() {
        return getInstance().FIRA_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       NUNITO
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getNunito() {
        return getInstance().NUNITO;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getNunitoBold() {
        return getInstance().NUNITO_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getNunitoItalic() {
        return getInstance().NUNITO_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getNunitoBoldItalic() {
        return getInstance().NUNITO_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       FANTASQUE
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getFantasque() {
        return getInstance().FANTASQUE;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getFantasqueBold() {
        return getInstance().FANTASQUE_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getFantasqueItalic() {
        return getInstance().FANTASQUE_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getFantasqueBoldItalic() {
        return getInstance().FANTASQUE_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       LATO
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getLato() {
        return getInstance().LATO;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getLatoBold() {
        return getInstance().LATO_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getLatoItalic() {
        return getInstance().LATO_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getLatoBoldItalic() {
        return getInstance().LATO_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       PT_SANS
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getPtSans() {
        return getInstance().PT_SANS;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getPtSansBold() {
        return getInstance().PT_SANS_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getPtSansItalic() {
        return getInstance().PT_SANS_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getPtSansBoldItalic() {
        return getInstance().PT_SANS_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       MONTSERRAT_ALTERNATES
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getMontserratAlternates() {
        return getInstance().MONTSERRAT_ALTERNATES;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getMontserratAlternatesBold() {
        return getInstance().MONTSERRAT_ALTERNATES_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getMontserratAlternatesItalic() {
        return getInstance().MONTSERRAT_ALTERNATES_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getMontserratAlternatesBoldItalic() {
        return getInstance().MONTSERRAT_ALTERNATES_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       OS_CONDENSED
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getOsCondensed() {
        return getInstance().OS_CONDENSED;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getOsCondensedBold() {
        return getInstance().OS_CONDENSED_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getOsCondensedItalic() {
        return getInstance().OS_CONDENSED_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getOsCondensedBoldItalic() {
        return getInstance().OS_CONDENSED_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       RUBIK
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getRubik() {
        return getInstance().RUBIK;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getRubikBold() {
        return getInstance().RUBIK_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getRubikItalic() {
        return getInstance().RUBIK_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getRubikBoldItalic() {
        return getInstance().RUBIK_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       PODKOVA
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getPodkova() {
        return getInstance().PODKOVA;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getPodkovaBold() {
        return getInstance().PODKOVA_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getPodkovaItalic() {
        return getInstance().PODKOVA_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getPodkovaBoldItalic() {
        return getInstance().PODKOVA_BOLD_ITALIC;
    }

}
