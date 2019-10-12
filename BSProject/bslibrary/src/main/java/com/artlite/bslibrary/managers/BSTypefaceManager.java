package com.artlite.bslibrary.managers;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
            = getFontPath("MontserratAlternates-BoldItalic.otf");

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

    /**
     * SOURCE_SANS_PRO
     */
    private static final String K_FONT_SOURCE_SANS_PRO
            = getFontPath("SourceSansPro-Regular.otf");
    private static final String K_FONT_SOURCE_SANS_PRO_BOLD
            = getFontPath("SourceSansPro-Bold.otf");
    private static final String K_FONT_SOURCE_SANS_PRO_ITALIC
            = getFontPath("SourceSansPro-It.otf");
    private static final String K_FONT_SOURCE_SANS_PRO_BOLD_ITALIC
            = getFontPath("SourceSansPro-BoldIt.otf");

    /**
     * ROBOTO_SLAB
     */
    private static final String K_FONT_ROBOTO_SLAB
            = getFontPath("RobotoSlab-Regular.ttf");
    private static final String K_FONT_ROBOTO_SLAB_BOLD
            = getFontPath("RobotoSlab-Bold.ttf");
    private static final String K_FONT_ROBOTO_SLAB_ITALIC
            = getFontPath("RobotoSlab-Regular.ttf");
    private static final String K_FONT_ROBOTO_SLAB_BOLD_ITALIC
            = getFontPath("RobotoSlab-Bold.ttf");

    /**
     * EXO_2
     */
    private static final String K_FONT_EXO_2
            = getFontPath("Exo2.0-Regular.otf");
    private static final String K_FONT_EXO_2_BOLD
            = getFontPath("Exo2.0-Bold.otf");
    private static final String K_FONT_EXO_2_ITALIC
            = getFontPath("Exo2.0-Italic.otf");
    private static final String K_FONT_EXO_2_BOLD_ITALIC
            = getFontPath("Exo2.0-BoldItalic.otf");

    /**
     * M_PLUS
     */
    private static final String K_FONT_M_PLUS
            = getFontPath("MPLUSRounded1c-Regular.ttf");
    private static final String K_FONT_M_PLUS_BOLD
            = getFontPath("MPLUSRounded1c-Bold.ttf");
    private static final String K_FONT_M_PLUS_ITALIC
            = getFontPath("MPLUSRounded1c-Regular.ttf");
    private static final String K_FONT_M_PLUS_BOLD_ITALIC
            = getFontPath("MPLUSRounded1c-Bold.ttf");

    /**
     * YANONE
     */
    private static final String K_FONT_YANONE
            = getFontPath("YanoneKaffeesatz-Regular.otf");
    private static final String K_FONT_YANONE_BOLD
            = getFontPath("YanoneKaffeesatz-Bold.otf");
    private static final String K_FONT_YANONE_ITALIC
            = getFontPath("YanoneKaffeesatz-Regular.otf");
    private static final String K_FONT_YANONE_BOLD_ITALIC
            = getFontPath("YanoneKaffeesatz-Bold.otf");

    /**
     * PRODUCT_SANS
     */
    private static final String K_FONT_PRODUCT_SANS
            = getFontPath("Product_Sans_Regular.ttf");
    private static final String K_FONT_PRODUCT_SANS_BOLD
            = getFontPath("Product_Sans_Bold.ttf");
    private static final String K_FONT_PRODUCT_SANS_ITALIC
            = getFontPath("Product_Sans_Italic.ttf");
    private static final String K_FONT_PRODUCT_SANS_BOLD_ITALIC
            = getFontPath("Product_Sans_Bold_Italic.ttf");

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
     * SOURCE_SANS_PRO
     */
    private Typeface SOURCE_SANS_PRO;
    private Typeface SOURCE_SANS_PRO_BOLD;
    private Typeface SOURCE_SANS_PRO_ITALIC;
    private Typeface SOURCE_SANS_PRO_BOLD_ITALIC;

    /**
     * ROBOTO_SLAB
     */
    private Typeface ROBOTO_SLAB;
    private Typeface ROBOTO_SLAB_BOLD;
    private Typeface ROBOTO_SLAB_ITALIC;
    private Typeface ROBOTO_SLAB_BOLD_ITALIC;

    /**
     * EXO_2
     */
    private Typeface EXO_2;
    private Typeface EXO_2_BOLD;
    private Typeface EXO_2_ITALIC;
    private Typeface EXO_2_BOLD_ITALIC;

    /**
     * M_PLUS
     */
    private Typeface M_PLUS;
    private Typeface M_PLUS_BOLD;
    private Typeface M_PLUS_ITALIC;
    private Typeface M_PLUS_BOLD_ITALIC;

    /**
     * YANONE
     */
    private Typeface YANONE;
    private Typeface YANONE_BOLD;
    private Typeface YANONE_ITALIC;
    private Typeface YANONE_BOLD_ITALIC;

    /**
     * PRODUCT_SANS
     */
    private Typeface PRODUCT_SANS;
    private Typeface PRODUCT_SANS_BOLD;
    private Typeface PRODUCT_SANS_ITALIC;
    private Typeface PRODUCT_SANS_BOLD_ITALIC;

    /**
     * Instance of the {@link BSTypeface}
     */
    private final BSTypeface typeface;

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
            instance = new BSTypefaceManager(context, typeface);
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
     * @param context  instance of {@link Context}
     * @param typeface instance of the {@link BSTypeface}
     */
    private BSTypefaceManager(@NonNull Context context,
                              @NonNull BSTypeface typeface) {
        super(context);
        this.typeface = typeface;
    }

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
            case SOURCE_SANS_PRO:
                return getSourceSansPro();
            case ROBOTO_SLAB:
                return getRobotoSlab();
            case EXO_2:
                return getExoSecond();
            case M_PLUS:
                return getMPlus();
            case YANONE:
                return getYanone();
            case PRODUCT_SANS:
                return getProductSans();
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
            case SOURCE_SANS_PRO:
                return getSourceSansProBold();
            case ROBOTO_SLAB:
                return getRobotoSlabBold();
            case EXO_2:
                return getExoSecondBold();
            case M_PLUS:
                return getMPlusBold();
            case YANONE:
                return getYanoneBold();
            case PRODUCT_SANS:
                return getProductSansBold();
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
            case SOURCE_SANS_PRO:
                return getSourceSansProItalic();
            case ROBOTO_SLAB:
                return getRobotoSlabItalic();
            case EXO_2:
                return getExoSecondItalic();
            case M_PLUS:
                return getMPlusItalic();
            case YANONE:
                return getYanoneItalic();
            case PRODUCT_SANS:
                return getProductSansItalic();
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
            case SOURCE_SANS_PRO:
                return getSourceSansProBoldItalic();
            case ROBOTO_SLAB:
                return getRobotoSlabBoldItalic();
            case EXO_2:
                return getExoSecondBoldItalic();
            case M_PLUS:
                return getMPlusBoldItalic();
            case YANONE:
                return getYanoneBoldItalic();
            case PRODUCT_SANS:
                return getProductSansBoldItalic();
        }
        return getOsBoldItalic();
    }

    //==============================================================================================
    //                                       ALLER(LOOKS GOOD)
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getAller() {
        if (getInstance().ALLER == null) {
            try {
                getInstance().ALLER = getTypeface(getInstance().getContext(),
                        K_FONT_ALLER);
            } catch (Exception ex) {
                Log.e(TAG, "getAller: ", ex);
            }
        }
        return getInstance().ALLER;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getAllerBold() {
        if (getInstance().ALLER_BOLD == null) {
            try {
                getInstance().ALLER_BOLD = getTypeface(getInstance().getContext(),
                        K_FONT_ALLER_BOLD);
            } catch (Exception ex) {
                Log.e(TAG, "getAllerBold: ", ex);
            }
        }
        return getInstance().ALLER_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getAllerItalic() {
        if (getInstance().ALLER_ITALIC == null) {
            try {
                getInstance().ALLER_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_ALLER_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getAllerItalic: ", ex);
            }
        }
        return getInstance().ALLER_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getAllerBoldItalic() {
        if (getInstance().ALLER_BOLD_ITALIC == null) {
            try {
                getInstance().ALLER_BOLD_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_ALLER_BOLD_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getAllerBoldItalic: ", ex);
            }
        }
        return getInstance().ALLER_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       ROBOTO(LOOKS GOOD)
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getRoboto() {
        if (getInstance().ROBOTO == null) {
            try {
                getInstance().ROBOTO = getTypeface(getInstance().getContext(),
                        K_FONT_ROBOTO);
            } catch (Exception ex) {
                Log.e(TAG, "getRoboto: ", ex);
            }
        }
        return getInstance().ROBOTO;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getRobotoBold() {
        if (getInstance().ROBOTO_BOLD == null) {
            try {
                getInstance().ROBOTO_BOLD = getTypeface(getInstance().getContext(),
                        K_FONT_ROBOTO_BOLD);
            } catch (Exception ex) {
                Log.e(TAG, "getRobotoBold: ", ex);
            }
        }
        return getInstance().ROBOTO_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getRobotoItalic() {
        if (getInstance().ROBOTO_ITALIC == null) {
            try {
                getInstance().ROBOTO_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_ROBOTO_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getRobotoItalic: ", ex);
            }
        }
        return getInstance().ROBOTO_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getRobotoBoldItalic() {
        if (getInstance().ROBOTO_BOLD_ITALIC == null) {
            try {
                getInstance().ROBOTO_BOLD_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_ROBOTO_BOLD_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getRobotoBoldItalic: ", ex);
            }
        }
        return getInstance().ROBOTO_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       OPEN SANS(LOOKS GOOD)
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getOs() {
        if (getInstance().OS == null) {
            try {
                getInstance().OS = getTypeface(getInstance().getContext(),
                        K_FONT_OS);
            } catch (Exception ex) {
                Log.e(TAG, "getOs: ", ex);
            }
        }
        return getInstance().OS;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getOsBold() {
        if (getInstance().OS_BOLD == null) {
            try {
                getInstance().OS_BOLD = getTypeface(getInstance().getContext(),
                        K_FONT_OS_BOLD);
            } catch (Exception ex) {
                Log.e(TAG, "getOsBold: ", ex);
            }
        }
        return getInstance().OS_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getOsItalic() {
        if (getInstance().OS_ITALIC == null) {
            try {
                getInstance().OS_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_OS_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getOsItalic: ", ex);
            }
        }
        return getInstance().OS_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getOsBoldItalic() {
        if (getInstance().OS_BOLD_ITALIC == null) {
            try {
                getInstance().OS_BOLD_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_OS_BOLD_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getOsBoldItalic: ", ex);
            }
        }
        return getInstance().OS_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       SAN FRANCISCO(LOOKS GOOD)
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getSf() {
        if (getInstance().SF == null) {
            try {
                getInstance().SF = getTypeface(getInstance().getContext(),
                        K_FONT_SF);
            } catch (Exception ex) {
                Log.e(TAG, "getSf: ", ex);
            }
        }
        return getInstance().SF;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getSfBold() {
        if (getInstance().SF_BOLD == null) {
            try {
                getInstance().SF_BOLD = getTypeface(getInstance().getContext(),
                        K_FONT_SF_BOLD);
            } catch (Exception ex) {
                Log.e(TAG, "getSfBold: ", ex);
            }
        }
        return getInstance().SF_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getSfItalic() {
        if (getInstance().SF_ITALIC == null) {
            try {
                getInstance().SF_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_SF_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getSfItalic: ", ex);
            }
        }
        return getInstance().SF_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getSfBoldItalic() {
        if (getInstance().SF_BOLD_ITALIC == null) {
            try {
                getInstance().SF_BOLD_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_SF_BOLD_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getSfBoldItalic: ", ex);
            }
        }
        return getInstance().SF_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       UBUNTU(SPECIFIC)
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getUbuntu() {
        if (getInstance().U == null) {
            try {
                getInstance().U = getTypeface(getInstance().getContext(),
                        K_FONT_U);
            } catch (Exception ex) {
                Log.e(TAG, "getUbuntu: ", ex);
            }
        }
        return getInstance().U;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getUbuntuBold() {
        if (getInstance().U_BOLD == null) {
            try {
                getInstance().U_BOLD = getTypeface(getInstance().getContext(),
                        K_FONT_U_BOLD);
            } catch (Exception ex) {
                Log.e(TAG, "getUbuntuBold: ", ex);
            }
        }
        return getInstance().U_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getUbuntuItalic() {
        if (getInstance().U_ITALIC == null) {
            try {
                getInstance().U_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_U_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getUbuntuItalic: ", ex);
            }
        }
        return getInstance().U_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getUbuntuBoldItalic() {
        if (getInstance().U_BOLD_ITALIC == null) {
            try {
                getInstance().U_BOLD_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_U_BOLD_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getUbuntuBoldItalic: ", ex);
            }
        }
        return getInstance().U_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       UBUNTU MONO(SPECIFIC)
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getUbuntuMono() {
        if (getInstance().UM == null) {
            try {
                getInstance().UM = getTypeface(getInstance().getContext(),
                        K_FONT_UM);
            } catch (Exception ex) {
                Log.e(TAG, "getUbuntuMono: ", ex);
            }
        }
        return getInstance().UM;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getUbuntuMonoBold() {
        if (getInstance().UM_BOLD == null) {
            try {
                getInstance().UM_BOLD = getTypeface(getInstance().getContext(),
                        K_FONT_UM_BOLD);
            } catch (Exception ex) {
                Log.e(TAG, "getUbuntuMonoBold: ", ex);
            }
        }
        return getInstance().UM_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getUbuntuMonoItalic() {
        if (getInstance().UM_ITALIC == null) {
            try {
                getInstance().UM_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_UM_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getUbuntuMonoItalic: ", ex);
            }
        }
        return getInstance().UM_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getUbuntuMonoBoldItalic() {
        if (getInstance().UM_BOLD_ITALIC == null) {
            try {
                getInstance().UM_BOLD_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_UM_BOLD_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getUbuntuMonoBoldItalic: ", ex);
            }
        }
        return getInstance().UM_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       BARIOL(CYRILIC ISN'T SUPPORTED)
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getBariol() {
        if (getInstance().BARIOL == null) {
            try {
                getInstance().BARIOL = getTypeface(getInstance().getContext(),
                        K_FONT_BARIOL);
            } catch (Exception ex) {
                Log.e(TAG, "getBariol: ", ex);
            }
        }
        return getInstance().BARIOL;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getBariolBold() {
        if (getInstance().BARIOL_BOLD == null) {
            try {
                getInstance().BARIOL_BOLD = getTypeface(getInstance().getContext(),
                        K_FONT_BARIOL_BOLD);
            } catch (Exception ex) {
                Log.e(TAG, "getBariolBold: ", ex);
            }
        }
        return getInstance().BARIOL_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getBariolItalic() {
        if (getInstance().BARIOL_ITALIC == null) {
            try {
                getInstance().BARIOL_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_BARIOL_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getBariolItalic: ", ex);
            }
        }
        return getInstance().BARIOL_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getBariolBoldItalic() {
        if (getInstance().BARIOL_BOLD_ITALIC == null) {
            try {
                getInstance().BARIOL_BOLD_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_BARIOL_BOLD_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getBariolBoldItalic: ", ex);
            }
        }
        return getInstance().BARIOL_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       COMFORTAA(LOOKS GOOD)
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getComfortaa() {
        if (getInstance().COMFORTAA == null) {
            try {
                getInstance().COMFORTAA = getTypeface(getInstance().getContext(),
                        K_FONT_COMFORTAA);
            } catch (Exception ex) {
                Log.e(TAG, "getComfortaa: ", ex);
            }
        }
        return getInstance().COMFORTAA;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getComfortaaBold() {
        if (getInstance().COMFORTAA_BOLD == null) {
            try {
                getInstance().COMFORTAA_BOLD = getTypeface(getInstance().getContext(),
                        K_FONT_COMFORTAA_BOLD);
            } catch (Exception ex) {
                Log.e(TAG, "getComfortaaBold: ", ex);
            }
        }
        return getInstance().COMFORTAA_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getComfortaaItalic() {
        if (getInstance().COMFORTAA_ITALIC == null) {
            try {
                getInstance().COMFORTAA_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_COMFORTAA_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getComfortaaItalic: ", ex);
            }
        }
        return getInstance().COMFORTAA_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getComfortaaBoldItalic() {
        if (getInstance().COMFORTAA_BOLD_ITALIC == null) {
            try {
                getInstance().COMFORTAA_BOLD_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_COMFORTAA_BOLD_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getComfortaaBoldItalic: ", ex);
            }
        }
        return getInstance().COMFORTAA_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       VR(SUPPORT ONLY REGULAR)
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getVR() {
        if (getInstance().VR == null) {
            try {
                getInstance().VR = getTypeface(getInstance().getContext(),
                        K_FONT_VR);
            } catch (Exception ex) {
                Log.e(TAG, "getVR: ", ex);
            }
        }
        return getInstance().VR;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getVRBold() {
        if (getInstance().VR_BOLD == null) {
            try {
                getInstance().VR_BOLD = getTypeface(getInstance().getContext(),
                        K_FONT_VR_BOLD);
            } catch (Exception ex) {
                Log.e(TAG, "getVRBold: ", ex);
            }
        }
        return getInstance().VR_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getVRItalic() {
        if (getInstance().VR_ITALIC == null) {
            try {
                getInstance().VR_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_VR_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getVRItalic: ", ex);
            }
        }
        return getInstance().VR_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getVRBoldItalic() {
        if (getInstance().VR_BOLD_ITALIC == null) {
            try {
                getInstance().VR_BOLD_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_VR_BOLD_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getVRBoldItalic: ", ex);
            }
        }
        return getInstance().VR_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       MONTSERAT(LOOKS GOOD)
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getMontserat() {
        if (getInstance().MONTSERAT == null) {
            try {
                getInstance().MONTSERAT = getTypeface(getInstance().getContext(),
                        K_FONT_MONTSERAT);
            } catch (Exception ex) {
                Log.e(TAG, "getMontserat: ", ex);
            }
        }
        return getInstance().MONTSERAT;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getMontseratBold() {
        if (getInstance().MONTSERAT_BOLD == null) {
            try {
                getInstance().MONTSERAT_BOLD = getTypeface(getInstance().getContext(),
                        K_FONT_MONTSERAT_BOLD);
            } catch (Exception ex) {
                Log.e(TAG, "getMontseratBold: ", ex);
            }
        }
        return getInstance().MONTSERAT_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getMontseratItalic() {
        if (getInstance().MONTSERAT_ITALIC == null) {
            try {
                getInstance().MONTSERAT_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_MONTSERAT_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getMontseratItalic: ", ex);
            }
        }
        return getInstance().MONTSERAT_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getMontseratBoldItalic() {
        if (getInstance().MONTSERAT_BOLD_ITALIC == null) {
            try {
                getInstance().MONTSERAT_BOLD_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_MONTSERAT_BOLD_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getMontseratBoldItalic: ", ex);
            }
        }
        return getInstance().MONTSERAT_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       BLOG_SANS(LOOKS GOOD)
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getBlogSans() {
        if (getInstance().BLOG_SANS == null) {
            try {
                getInstance().BLOG_SANS = getTypeface(getInstance().getContext(),
                        K_FONT_BLOG_SANS);
            } catch (Exception ex) {
                Log.e(TAG, "getBlogSans: ", ex);
            }
        }
        return getInstance().BLOG_SANS;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getBlogSansBold() {
        if (getInstance().BLOG_SANS_BOLD == null) {
            try {
                getInstance().BLOG_SANS_BOLD = getTypeface(getInstance().getContext(),
                        K_FONT_BLOG_SANS_BOLD);
            } catch (Exception ex) {
                Log.e(TAG, "getBlogSansBold: ", ex);
            }
        }
        return getInstance().BLOG_SANS_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getBlogSansItalic() {
        if (getInstance().BLOG_SANS_ITALIC == null) {
            try {
                getInstance().BLOG_SANS_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_BLOG_SANS_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getBlogSansItalic: ", ex);
            }
        }
        return getInstance().BLOG_SANS_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getBlogSansBoldItalic() {
        if (getInstance().BLOG_SANS_BOLD_ITALIC == null) {
            try {
                getInstance().BLOG_SANS_BOLD_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_BLOG_SANS_BOLD_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getBlogSansBoldItalic: ", ex);
            }
        }
        return getInstance().BLOG_SANS_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       CLEAR_SANS(LOOKS GOOD)
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getClearSans() {
        if (getInstance().CLEAR_SANS == null) {
            try {
                getInstance().CLEAR_SANS = getTypeface(getInstance().getContext(),
                        K_FONT_CLEAR_SANS);
            } catch (Exception ex) {
                Log.e(TAG, "getClearSans: ", ex);
            }
        }
        return getInstance().CLEAR_SANS;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getClearSansBold() {
        if (getInstance().CLEAR_SANS_BOLD == null) {
            try {
                getInstance().CLEAR_SANS_BOLD = getTypeface(getInstance().getContext(),
                        K_FONT_CLEAR_SANS_BOLD);
            } catch (Exception ex) {
                Log.e(TAG, "getClearSansBold: ", ex);
            }
        }
        return getInstance().CLEAR_SANS_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getClearSansItalic() {
        if (getInstance().CLEAR_SANS_ITALIC == null) {
            try {
                getInstance().CLEAR_SANS_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_CLEAR_SANS_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getClearSansItalic: ", ex);
            }
        }
        return getInstance().CLEAR_SANS_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getClearSansBoldItalic() {
        if (getInstance().CLEAR_SANS_BOLD_ITALIC == null) {
            try {
                getInstance().CLEAR_SANS_BOLD_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_CLEAR_SANS_BOLD_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getClearSansBoldItalic: ", ex);
            }
        }
        return getInstance().CLEAR_SANS_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       ROBOTO_CONSENDET(LOOKS GOOD)
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getRobotoCondensed() {
        if (getInstance().ROBOTO_CONSENDET == null) {
            try {
                getInstance().ROBOTO_CONSENDET = getTypeface(getInstance().getContext(),
                        K_FONT_ROBOTO_CONSENDET);
            } catch (Exception ex) {
                Log.e(TAG, "getRobotoCondensed: ", ex);
            }
        }
        return getInstance().ROBOTO_CONSENDET;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getRobotoCondensedBold() {
        if (getInstance().ROBOTO_CONSENDET_BOLD == null) {
            try {
                getInstance().ROBOTO_CONSENDET_BOLD = getTypeface(getInstance().getContext(),
                        K_FONT_ROBOTO_CONSENDET_BOLD);
            } catch (Exception ex) {
                Log.e(TAG, "getRobotoCondensedBold: ", ex);
            }
        }
        return getInstance().ROBOTO_CONSENDET_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getRobotoCondensedItalic() {
        if (getInstance().ROBOTO_CONSENDET_ITALIC == null) {
            try {
                getInstance().ROBOTO_CONSENDET_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_ROBOTO_CONSENDET_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getRobotoCondensedItalic: ", ex);
            }
        }
        return getInstance().ROBOTO_CONSENDET_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getRobotoCondensedBoldItalic() {
        if (getInstance().ROBOTO_CONSENDET_BOLD_ITALIC == null) {
            try {
                getInstance().ROBOTO_CONSENDET_BOLD_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_ROBOTO_CONSENDET_BOLD_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getRobotoCondensedBoldItalic: ", ex);
            }
        }
        return getInstance().ROBOTO_CONSENDET_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       DJV(LOOKS GOOD)
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getDjv() {
        if (getInstance().DJV == null) {
            try {
                getInstance().DJV = getTypeface(getInstance().getContext(),
                        K_FONT_DJV);
            } catch (Exception ex) {
                Log.e(TAG, "getDjv: ", ex);
            }
        }
        return getInstance().DJV;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getDjvBold() {
        if (getInstance().DJV_BOLD == null) {
            try {
                getInstance().DJV_BOLD = getTypeface(getInstance().getContext(),
                        K_FONT_DJV_BOLD);
            } catch (Exception ex) {
                Log.e(TAG, "getDjvBold: ", ex);
            }
        }
        return getInstance().DJV_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getDjvItalic() {
        if (getInstance().DJV_ITALIC == null) {
            try {
                getInstance().DJV_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_DJV_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getDjvItalic: ", ex);
            }
        }
        return getInstance().DJV_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getDjvBoldItalic() {
        if (getInstance().DJV_BOLD_ITALIC == null) {
            try {
                getInstance().DJV_BOLD_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_DJV_BOLD_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getDjvBoldItalic: ", ex);
            }
        }
        return getInstance().DJV_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       SFC(LOOKS GOOD)
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getSfc() {
        if (getInstance().SFC == null) {
            try {
                getInstance().SFC = getTypeface(getInstance().getContext(),
                        K_FONT_SFC);
            } catch (Exception ex) {
                Log.e(TAG, "getSfc: ", ex);
            }
        }
        return getInstance().SFC;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getSfcBold() {
        if (getInstance().SFC_BOLD == null) {
            try {
                getInstance().SFC_BOLD = getTypeface(getInstance().getContext(),
                        K_FONT_SFC_BOLD);
            } catch (Exception ex) {
                Log.e(TAG, "getSfcBold: ", ex);
            }
        }
        return getInstance().SFC_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getSfcItalic() {
        if (getInstance().SFC_ITALIC == null) {
            try {
                getInstance().SFC_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_SFC_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getSfcItalic: ", ex);
            }
        }
        return getInstance().SFC_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getSfcBoldItalic() {
        if (getInstance().SFC_BOLD_ITALIC == null) {
            try {
                getInstance().SFC_BOLD_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_SFC_BOLD_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getSfcBoldItalic: ", ex);
            }
        }
        return getInstance().SFC_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       GS(LOOKS GOOD)
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getGs() {
        if (getInstance().GS == null) {
            try {
                getInstance().GS = getTypeface(getInstance().getContext(),
                        K_FONT_GS);
            } catch (Exception ex) {
                Log.e(TAG, "getGs: ", ex);
            }
        }
        return getInstance().GS;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getGsBold() {
        if (getInstance().GS_BOLD == null) {
            try {
                getInstance().GS_BOLD = getTypeface(getInstance().getContext(),
                        K_FONT_GS_BOLD);
            } catch (Exception ex) {
                Log.e(TAG, "getGsBold: ", ex);
            }
        }
        return getInstance().GS_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getGsItalic() {
        if (getInstance().GS_ITALIC == null) {
            try {
                getInstance().GS_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_GS_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getGsItalic: ", ex);
            }
        }
        return getInstance().GS_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getGsBoldItalic() {
        if (getInstance().GS_BOLD_ITALIC == null) {
            try {
                getInstance().GS_BOLD_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_GS_BOLD_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getGsBoldItalic: ", ex);
            }
        }
        return getInstance().GS_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       FIRA(LOOKS GOOD)
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getFira() {
        if (getInstance().FIRA == null) {
            try {
                getInstance().FIRA = getTypeface(getInstance().getContext(),
                        K_FONT_FIRA);
            } catch (Exception ex) {
                Log.e(TAG, "getFira: ", ex);
            }
        }
        return getInstance().FIRA;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getFiraBold() {
        if (getInstance().FIRA_BOLD == null) {
            try {
                getInstance().FIRA_BOLD = getTypeface(getInstance().getContext(),
                        K_FONT_FIRA_BOLD);
            } catch (Exception ex) {
                Log.e(TAG, "getFiraBold: ", ex);
            }
        }
        return getInstance().FIRA_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getFiraItalic() {
        if (getInstance().FIRA_ITALIC == null) {
            try {
                getInstance().FIRA_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_FIRA_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getFiraItalic: ", ex);
            }
        }
        return getInstance().FIRA_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getFiraBoldItalic() {
        if (getInstance().FIRA_BOLD_ITALIC == null) {
            try {
                getInstance().FIRA_BOLD_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_FIRA_BOLD_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getFiraBoldItalic: ", ex);
            }
        }
        return getInstance().FIRA_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       NUNITO(HAVE ONLY BOLD AND REGULAR)
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getNunito() {
        if (getInstance().NUNITO == null) {
            try {
                getInstance().NUNITO = getTypeface(getInstance().getContext(),
                        K_FONT_NUNITO);
            } catch (Exception ex) {
                Log.e(TAG, "getNunito: ", ex);
            }
        }
        return getInstance().NUNITO;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getNunitoBold() {
        if (getInstance().NUNITO_BOLD == null) {
            try {
                getInstance().NUNITO_BOLD = getTypeface(getInstance().getContext(),
                        K_FONT_NUNITO_BOLD);
            } catch (Exception ex) {
                Log.e(TAG, "getNunitoBold: ", ex);
            }
        }
        return getInstance().NUNITO_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getNunitoItalic() {
        if (getInstance().NUNITO_ITALIC == null) {
            try {
                getInstance().NUNITO_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_NUNITO_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getNunitoItalic: ", ex);
            }
        }
        return getInstance().NUNITO_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getNunitoBoldItalic() {
        if (getInstance().NUNITO_BOLD_ITALIC == null) {
            try {
                getInstance().NUNITO_BOLD_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_NUNITO_BOLD_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getNunitoBoldItalic: ", ex);
            }
        }
        return getInstance().NUNITO_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       FANTASQUE(SPECIFIC)
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getFantasque() {
        if (getInstance().FANTASQUE == null) {
            try {
                getInstance().FANTASQUE = getTypeface(getInstance().getContext(),
                        K_FONT_FANTASQUE);
            } catch (Exception ex) {
                Log.e(TAG, "getFantasque: ", ex);
            }
        }
        return getInstance().FANTASQUE;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getFantasqueBold() {
        if (getInstance().FANTASQUE_BOLD == null) {
            try {
                getInstance().FANTASQUE_BOLD = getTypeface(getInstance().getContext(),
                        K_FONT_FANTASQUE_BOLD);
            } catch (Exception ex) {
                Log.e(TAG, "getFantasqueBold: ", ex);
            }
        }
        return getInstance().FANTASQUE_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getFantasqueItalic() {
        if (getInstance().FANTASQUE_ITALIC == null) {
            try {
                getInstance().FANTASQUE_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_FANTASQUE_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getFantasqueItalic: ", ex);
            }
        }
        return getInstance().FANTASQUE_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getFantasqueBoldItalic() {
        if (getInstance().FANTASQUE_BOLD_ITALIC == null) {
            try {
                getInstance().FANTASQUE_BOLD_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_FANTASQUE_BOLD_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getFantasqueBoldItalic: ", ex);
            }
        }
        return getInstance().FANTASQUE_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       LATO(LOOKS NICE)
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getLato() {
        if (getInstance().LATO == null) {
            try {
                getInstance().LATO = getTypeface(getInstance().getContext(),
                        K_FONT_LATO);
            } catch (Exception ex) {
                Log.e(TAG, "getLato: ", ex);
            }
        }
        return getInstance().LATO;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getLatoBold() {
        if (getInstance().LATO_BOLD == null) {
            try {
                getInstance().LATO_BOLD = getTypeface(getInstance().getContext(),
                        K_FONT_LATO_BOLD);
            } catch (Exception ex) {
                Log.e(TAG, "getLatoBold: ", ex);
            }
        }
        return getInstance().LATO_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getLatoItalic() {
        if (getInstance().LATO_ITALIC == null) {
            try {
                getInstance().LATO_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_LATO_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getLatoItalic: ", ex);
            }
        }
        return getInstance().LATO_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getLatoBoldItalic() {
        if (getInstance().LATO_BOLD_ITALIC == null) {
            try {
                getInstance().LATO_BOLD_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_LATO_BOLD_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getLatoBoldItalic: ", ex);
            }
        }
        return getInstance().LATO_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       PT_SANS(LOOKS GOOD)
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getPtSans() {
        if (getInstance().PT_SANS == null) {
            try {
                getInstance().PT_SANS = getTypeface(getInstance().getContext(),
                        K_FONT_PT_SANS);
            } catch (Exception ex) {
                Log.e(TAG, "getPtSans: ", ex);
            }
        }
        return getInstance().PT_SANS;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getPtSansBold() {
        if (getInstance().PT_SANS_BOLD == null) {
            try {
                getInstance().PT_SANS_BOLD = getTypeface(getInstance().getContext(),
                        K_FONT_PT_SANS_BOLD);
            } catch (Exception ex) {
                Log.e(TAG, "getPtSansBold: ", ex);
            }
        }
        return getInstance().PT_SANS_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getPtSansItalic() {
        if (getInstance().PT_SANS_ITALIC == null) {
            try {
                getInstance().PT_SANS_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_PT_SANS_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getPtSansItalic: ", ex);
            }
        }
        return getInstance().PT_SANS_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getPtSansBoldItalic() {
        if (getInstance().PT_SANS_BOLD_ITALIC == null) {
            try {
                getInstance().PT_SANS_BOLD_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_PT_SANS_BOLD_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getPtSansBoldItalic: ", ex);
            }
        }
        return getInstance().PT_SANS_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       MONTSERRAT_ALTERNATES(LOOKS GOOD)
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getMontserratAlternates() {
        if (getInstance().MONTSERRAT_ALTERNATES == null) {
            try {
                getInstance().MONTSERRAT_ALTERNATES = getTypeface(getInstance().getContext(),
                        K_FONT_MONTSERRAT_ALTERNATES);
            } catch (Exception ex) {
                Log.e(TAG, "getMontserratAlternates: ", ex);
            }
        }
        return getInstance().MONTSERRAT_ALTERNATES;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getMontserratAlternatesBold() {
        if (getInstance().MONTSERRAT_ALTERNATES_BOLD == null) {
            try {
                getInstance().MONTSERRAT_ALTERNATES_BOLD = getTypeface(getInstance().getContext(),
                        K_FONT_MONTSERRAT_ALTERNATES_BOLD);
            } catch (Exception ex) {
                Log.e(TAG, "getMontserratAlternatesBold: ", ex);
            }
        }
        return getInstance().MONTSERRAT_ALTERNATES_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getMontserratAlternatesItalic() {
        if (getInstance().MONTSERRAT_ALTERNATES_ITALIC == null) {
            try {
                getInstance().MONTSERRAT_ALTERNATES_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_MONTSERRAT_ALTERNATES_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getMontserratAlternatesItalic: ", ex);
            }
        }
        return getInstance().MONTSERRAT_ALTERNATES_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getMontserratAlternatesBoldItalic() {
        if (getInstance().MONTSERRAT_ALTERNATES_BOLD_ITALIC == null) {
            try {
                getInstance().MONTSERRAT_ALTERNATES_BOLD_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_MONTSERRAT_ALTERNATES_BOLD_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getMontserratAlternatesBoldItalic: ", ex);
            }
        }
        return getInstance().MONTSERRAT_ALTERNATES_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       OS_CONDENSED(LOOKS SPECIFIC)
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getOsCondensed() {
        if (getInstance().OS_CONDENSED == null) {
            try {
                getInstance().OS_CONDENSED = getTypeface(getInstance().getContext(),
                        K_FONT_OS_CONDENSED);
            } catch (Exception ex) {
                Log.e(TAG, "getOsCondensed: ", ex);
            }
        }
        return getInstance().OS_CONDENSED;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getOsCondensedBold() {
        if (getInstance().OS_CONDENSED_BOLD == null) {
            try {
                getInstance().OS_CONDENSED_BOLD = getTypeface(getInstance().getContext(),
                        K_FONT_OS_CONDENSED_BOLD);
            } catch (Exception ex) {
                Log.e(TAG, "getOsCondensedBold: ", ex);
            }
        }
        return getInstance().OS_CONDENSED_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getOsCondensedItalic() {
        if (getInstance().OS_CONDENSED_ITALIC == null) {
            try {
                getInstance().OS_CONDENSED_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_OS_CONDENSED_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getOsCondensedItalic: ", ex);
            }
        }
        return getInstance().OS_CONDENSED_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getOsCondensedBoldItalic() {
        if (getInstance().OS_CONDENSED_BOLD_ITALIC == null) {
            try {
                getInstance().OS_CONDENSED_BOLD_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_OS_CONDENSED_BOLD_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getOsCondensedBoldItalic: ", ex);
            }
        }
        return getInstance().OS_CONDENSED_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       RUBIK(LOOKS NICE)
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getRubik() {
        if (getInstance().RUBIK == null) {
            try {
                getInstance().RUBIK = getTypeface(getInstance().getContext(),
                        K_FONT_RUBIK);
            } catch (Exception ex) {
                Log.e(TAG, "getRubik: ", ex);
            }
        }
        return getInstance().RUBIK;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getRubikBold() {
        if (getInstance().RUBIK_BOLD == null) {
            try {
                getInstance().RUBIK_BOLD = getTypeface(getInstance().getContext(),
                        K_FONT_RUBIK_BOLD);
            } catch (Exception ex) {
                Log.e(TAG, "getRubikBold: ", ex);
            }
        }
        return getInstance().RUBIK_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getRubikItalic() {
        if (getInstance().RUBIK_ITALIC == null) {
            try {
                getInstance().RUBIK_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_RUBIK_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getRubikItalic: ", ex);
            }
        }
        return getInstance().RUBIK_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getRubikBoldItalic() {
        if (getInstance().RUBIK_BOLD_ITALIC == null) {
            try {
                getInstance().RUBIK_BOLD_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_RUBIK_BOLD_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getRubikBoldItalic: ", ex);
            }
        }
        return getInstance().RUBIK_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       PODKOVA(HAS ONLY BOLD AND REGULAR)
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getPodkova() {
        if (getInstance().PODKOVA == null) {
            try {
                getInstance().PODKOVA = getTypeface(getInstance().getContext(),
                        K_FONT_PODKOVA);
            } catch (Exception ex) {
                Log.e(TAG, "getPodkova: ", ex);
            }
        }
        return getInstance().PODKOVA;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getPodkovaBold() {
        if (getInstance().PODKOVA_BOLD == null) {
            try {
                getInstance().PODKOVA_BOLD = getTypeface(getInstance().getContext(),
                        K_FONT_PODKOVA_BOLD);
            } catch (Exception ex) {
                Log.e(TAG, "getPodkovaBold: ", ex);
            }
        }
        return getInstance().PODKOVA_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getPodkovaItalic() {
        if (getInstance().PODKOVA_ITALIC == null) {
            try {
                getInstance().PODKOVA_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_PODKOVA_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getPodkovaItalic: ", ex);
            }
        }
        return getInstance().PODKOVA_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getPodkovaBoldItalic() {
        if (getInstance().PODKOVA_BOLD_ITALIC == null) {
            try {
                getInstance().PODKOVA_BOLD_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_PODKOVA_BOLD_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getPodkovaBoldItalic: ", ex);
            }
        }
        return getInstance().PODKOVA_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       SOURCE_SANS_PRO(LOOKS GOOD)
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getSourceSansPro() {
        if (getInstance().SOURCE_SANS_PRO == null) {
            try {
                getInstance().SOURCE_SANS_PRO = getTypeface(getInstance().getContext(),
                        K_FONT_SOURCE_SANS_PRO);
            } catch (Exception ex) {
                Log.e(TAG, "getSourceSansPro: ", ex);
            }
        }
        return getInstance().SOURCE_SANS_PRO;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getSourceSansProBold() {
        if (getInstance().SOURCE_SANS_PRO_BOLD == null) {
            try {
                getInstance().SOURCE_SANS_PRO_BOLD = getTypeface(getInstance().getContext(),
                        K_FONT_SOURCE_SANS_PRO_BOLD);
            } catch (Exception ex) {
                Log.e(TAG, "getSourceSansProBold: ", ex);
            }
        }
        return getInstance().SOURCE_SANS_PRO_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getSourceSansProItalic() {
        if (getInstance().SOURCE_SANS_PRO_ITALIC == null) {
            try {
                getInstance().SOURCE_SANS_PRO_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_SOURCE_SANS_PRO_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getSourceSansProItalic: ", ex);
            }
        }
        return getInstance().SOURCE_SANS_PRO_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getSourceSansProBoldItalic() {
        if (getInstance().SOURCE_SANS_PRO_BOLD_ITALIC == null) {
            try {
                getInstance().SOURCE_SANS_PRO_BOLD_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_SOURCE_SANS_PRO_BOLD_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getSourceSansProBoldItalic: ", ex);
            }
        }
        return getInstance().SOURCE_SANS_PRO_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       ROBOTO_SLAB (HAVE ONLY REGULAR AND BOLD)
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getRobotoSlab() {
        if (getInstance().ROBOTO_SLAB == null) {
            try {
                getInstance().ROBOTO_SLAB = getTypeface(getInstance().getContext(),
                        K_FONT_ROBOTO_SLAB);
            } catch (Exception ex) {
                Log.e(TAG, "getRobotoSlab: ", ex);
            }
        }
        return getInstance().ROBOTO_SLAB;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getRobotoSlabBold() {
        if (getInstance().ROBOTO_SLAB_BOLD == null) {
            try {
                getInstance().ROBOTO_SLAB_BOLD = getTypeface(getInstance().getContext(),
                        K_FONT_ROBOTO_SLAB_BOLD);
            } catch (Exception ex) {
                Log.e(TAG, "getRobotoSlabBold: ", ex);
            }
        }
        return getInstance().ROBOTO_SLAB_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getRobotoSlabItalic() {
        if (getInstance().ROBOTO_SLAB_ITALIC == null) {
            try {
                getInstance().ROBOTO_SLAB_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_ROBOTO_SLAB_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getRobotoSlabItalic: ", ex);
            }
        }
        return getInstance().ROBOTO_SLAB_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getRobotoSlabBoldItalic() {
        if (getInstance().ROBOTO_SLAB_BOLD_ITALIC == null) {
            try {
                getInstance().ROBOTO_SLAB_BOLD_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_ROBOTO_SLAB_BOLD_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getRobotoSlabBoldItalic: ", ex);
            }
        }
        return getInstance().ROBOTO_SLAB_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       EXO_2 (LOOKS GOOD)
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getExoSecond() {
        if (getInstance().EXO_2 == null) {
            try {
                getInstance().EXO_2 = getTypeface(getInstance().getContext(),
                        K_FONT_EXO_2);
            } catch (Exception ex) {
                Log.e(TAG, "getExoSecond: ", ex);
            }
        }
        return getInstance().EXO_2;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getExoSecondBold() {
        if (getInstance().EXO_2_BOLD == null) {
            try {
                getInstance().EXO_2_BOLD = getTypeface(getInstance().getContext(),
                        K_FONT_EXO_2_BOLD);
            } catch (Exception ex) {
                Log.e(TAG, "getExoSecondBold: ", ex);
            }
        }
        return getInstance().EXO_2_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getExoSecondItalic() {
        if (getInstance().EXO_2_ITALIC == null) {
            try {
                getInstance().EXO_2_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_EXO_2_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getExoSecondItalic: ", ex);
            }
        }
        return getInstance().EXO_2_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getExoSecondBoldItalic() {
        if (getInstance().EXO_2_BOLD_ITALIC == null) {
            try {
                getInstance().EXO_2_BOLD_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_EXO_2_BOLD_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getExoSecondBoldItalic: ", ex);
            }
        }
        return getInstance().EXO_2_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       M_PLUS
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getMPlus() {
        if (getInstance().M_PLUS == null) {
            try {
                getInstance().M_PLUS = getTypeface(getInstance().getContext(),
                        K_FONT_M_PLUS);
            } catch (Exception ex) {
                Log.e(TAG, "getMPlus: ", ex);
            }
        }
        return getInstance().M_PLUS;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getMPlusBold() {
        if (getInstance().M_PLUS_BOLD == null) {
            try {
                getInstance().M_PLUS_BOLD = getTypeface(getInstance().getContext(),
                        K_FONT_M_PLUS_BOLD);
            } catch (Exception ex) {
                Log.e(TAG, "getMPlusBold: ", ex);
            }
        }
        return getInstance().M_PLUS_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getMPlusItalic() {
        if (getInstance().M_PLUS_ITALIC == null) {
            try {
                getInstance().M_PLUS_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_M_PLUS_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getMPlusItalic: ", ex);
            }
        }
        return getInstance().M_PLUS_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getMPlusBoldItalic() {
        if (getInstance().M_PLUS_BOLD_ITALIC == null) {
            try {
                getInstance().M_PLUS_BOLD_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_M_PLUS_BOLD_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getMPlusBoldItalic: ", ex);
            }
        }
        return getInstance().M_PLUS_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       YANONE(SPECIFIC)
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getYanone() {
        if (getInstance().YANONE == null) {
            try {
                getInstance().YANONE = getTypeface(getInstance().getContext(),
                        K_FONT_YANONE);
            } catch (Exception ex) {
                Log.e(TAG, "getYanone: ", ex);
            }
        }
        return getInstance().YANONE;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getYanoneBold() {
        if (getInstance().YANONE_BOLD == null) {
            try {
                getInstance().YANONE_BOLD = getTypeface(getInstance().getContext(),
                        K_FONT_YANONE_BOLD);
            } catch (Exception ex) {
                Log.e(TAG, "getYanoneBold: ", ex);
            }
        }
        return getInstance().YANONE_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getYanoneItalic() {
        if (getInstance().YANONE_ITALIC == null) {
            try {
                getInstance().YANONE_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_YANONE_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getYanoneItalic: ", ex);
            }
        }
        return getInstance().YANONE_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getYanoneBoldItalic() {
        if (getInstance().YANONE_BOLD_ITALIC == null) {
            try {
                getInstance().YANONE_BOLD_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_YANONE_BOLD_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getYanoneBoldItalic: ", ex);
            }
        }
        return getInstance().YANONE_BOLD_ITALIC;
    }

    //==============================================================================================
    //                                       PRODUCT_SANS(BEST FONT)
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getProductSans() {
        if (getInstance().PRODUCT_SANS == null) {
            try {
                getInstance().PRODUCT_SANS = getTypeface(getInstance().getContext(),
                        K_FONT_PRODUCT_SANS);
            } catch (Exception ex) {
                Log.e(TAG, "getProductSans: ", ex);
            }
        }
        return getInstance().PRODUCT_SANS;
    }


    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getProductSansBold() {
        if (getInstance().PRODUCT_SANS_BOLD == null) {
            try {
                getInstance().PRODUCT_SANS_BOLD = getTypeface(getInstance().getContext(),
                        K_FONT_PRODUCT_SANS_BOLD);
            } catch (Exception ex) {
                Log.e(TAG, "getProductSansBold: ", ex);
            }
        }
        return getInstance().PRODUCT_SANS_BOLD;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getProductSansItalic() {
        if (getInstance().PRODUCT_SANS_ITALIC == null) {
            try {
                getInstance().PRODUCT_SANS_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_PRODUCT_SANS_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getProductSansItalic: ", ex);
            }
        }
        return getInstance().PRODUCT_SANS_ITALIC;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getProductSansBoldItalic() {
        if (getInstance().PRODUCT_SANS_BOLD_ITALIC == null) {
            try {
                getInstance().PRODUCT_SANS_BOLD_ITALIC = getTypeface(getInstance().getContext(),
                        K_FONT_PRODUCT_SANS_BOLD_ITALIC);
            } catch (Exception ex) {
                Log.e(TAG, "getProductSansBoldItalic: ", ex);
            }
        }
        return getInstance().PRODUCT_SANS_BOLD_ITALIC;
    }

}
