package com.artlite.bslibrary.helpers.validation;

import android.support.annotation.Nullable;
import android.util.Patterns;
import android.widget.EditText;

import com.artlite.bslibrary.helpers.abs.BSBaseHelper;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Map;

/**
 * Method which provide the validating
 */

public final class BSValidationHelper extends BSBaseHelper {

    //==============================================================================================
    //                                      EMPTY/NULL
    //==============================================================================================

    /**
     * Method which provide the validation for null elements
     *
     * @param objects objects for validate
     * @return validation result
     */
    public static boolean isNull(@Nullable final Object... objects) {
        return !validateNull(objects);
    }

    /**
     * Method which provide the validation for null elements
     *
     * @param objects objects for validate
     * @return validation result
     * @deprecated use the {@link #validateNull(Object...)}
     */
    @Deprecated
    public static boolean nullValidate(@Nullable final Object... objects) {
        return validateNull(objects);
    }

    /**
     * Method which provide the validation for null elements
     *
     * @param objects objects for validate
     * @return validation result
     */
    public static boolean validateNull(@Nullable final Object... objects) {
        if (objects == null) {
            return false;
        }
        for (Object object : objects) {
            if (object == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method which provide the empty validations
     *
     * @param objects objects for validate
     * @return validation results
     */
    public static boolean isEmpty(@Nullable final Object... objects) {
        return !validateEmpty(objects);
    }

    /**
     * Method which provide the empty validations
     *
     * @param objects objects for validate
     * @return validation results
     * @deprecated use the {@link #validateEmpty(Object...)}
     */
    @Deprecated
    public static boolean emptyValidate(@Nullable final Object... objects) {
        return validateEmpty(objects);
    }

    /**
     * Method which provide the empty validations
     *
     * @param objects objects for validate
     * @return validation results
     */
    public static boolean validateEmpty(@Nullable final Object... objects) {
        if (objects == null) {
            return false;
        }
        if (objects.length == 0) {
            return false;
        }
        for (Object object : objects) {
            if (object == null) {
                return false;
            } else if (object instanceof Collection) {
                //List checking
                if (((Collection) object).isEmpty() == true) {
                    return false;
                }
            } else if (object instanceof Object[]) {
                //Array validate
                if (((Object[]) object).length == 0) {
                    return false;
                }
            } else if (object instanceof String) {
                //String validate
                if (((String) object).isEmpty() == true) {
                    return false;
                }
            } else if (object instanceof Map) {
                //Map validate
                if (((Map) object).isEmpty() == true) {
                    return false;
                }
            } else if (object instanceof WeakReference) {
                //Map validate
                if (((WeakReference) object).get() == null) {
                    return false;
                }
            }
        }
        return true;
    }

    //==============================================================================================
    //                                      PHONE
    //==============================================================================================

    /**
     * Method which provide the phone validation
     *
     * @param phone {@link String} value of the phone
     * @return validation result
     */
    public static boolean validatePhone(@Nullable final String phone) {
        if (isEmpty(phone)) {
            return false;
        }
        return Patterns.PHONE.matcher(phone).matches();
    }

    //==============================================================================================
    //                                      EMAIL
    //==============================================================================================

    /**
     * Method which provide the phone validation
     *
     * @param email {@link String} value of the email
     * @return validation result
     */
    public static boolean validateEmail(@Nullable final String email) {
        if (isEmpty(email)) {
            return false;
        }
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    //==============================================================================================
    //                                     PASSWORDS
    //==============================================================================================

    /**
     * Method which provide validate two passwords before user registration (for example)
     *
     * @param password {@link EditText} with password
     * @param repeat   {@link EditText} with repeated password
     * @return validation results
     */
    public static boolean validatePasswords(@Nullable final EditText password,
                                            @Nullable final EditText repeat) {
        return validatePasswords(password, repeat, 1, Integer.MAX_VALUE);
    }

    /**
     * Method which provide validate two passwords before user registration (for example)
     *
     * @param password      {@link EditText} with password
     * @param repeat        {@link EditText} with repeated password
     * @param minimumLength {@link Integer} value of the minimum length for the password
     *                      (default is 1)
     * @param maximumLength {@link Integer} value of the maximum length for the password
     *                      (default is Integer.MAX_VALUE)
     * @return validation results
     */
    public static boolean validatePasswords(@Nullable final EditText password,
                                            @Nullable final EditText repeat,
                                            int minimumLength,
                                            int maximumLength) {
        if (isEmpty(password, repeat)) {
            return false;
        }
        return validatePasswords(password.getText().toString().trim(),
                repeat.getText().toString().trim(), minimumLength, maximumLength);
    }

    /**
     * Method which provide validate two passwords before user registration (for example)
     *
     * @param password {@link String} value of the password
     * @param repeat   {@link String} value of the repeat password
     * @return validation results
     */
    public static boolean validatePasswords(@Nullable final String password,
                                            @Nullable final String repeat) {
        return validatePasswords(password, repeat, 1, Integer.MAX_VALUE);
    }

    /**
     * Method which provide validate two passwords before user registration (for example)
     *
     * @param password      {@link String} value of the password
     * @param repeat        {@link String} value of the repeat password
     * @param minimumLength {@link Integer} value of the minimum length for the password
     *                      (default is 1)
     * @param maximumLength {@link Integer} value of the maximum length for the password
     *                      (default is Integer.MAX_VALUE)
     * @return validation results
     */
    public static boolean validatePasswords(@Nullable final String password,
                                            @Nullable final String repeat,
                                            int minimumLength,
                                            int maximumLength) {
        final String methodName = "validatePasswords(password, repeat, minimumLength, maximumLength)";
        if (isEmpty(password, repeat)) {
            log(null, methodName, null, "Password or repeat password is empty");
            return false;
        }
        int passwordLength = password.length();
        int repeatLength = repeat.length();
        if ((passwordLength < minimumLength) || (passwordLength > maximumLength)) {
            log(null, methodName, null, "Password length is less than minimum length " +
                    "or more than maximum length");
            return false;
        }
        if (!password.equals(repeat)) {
            log(null, methodName, null, "Password and repeat password is different");
            return false;
        }
        return true;
    }
}
