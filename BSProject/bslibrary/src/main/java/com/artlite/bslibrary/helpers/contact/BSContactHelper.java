package com.artlite.bslibrary.helpers.contact;

import android.annotation.SuppressLint;
import android.content.ContentProviderOperation;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.util.Log;

import com.artlite.bslibrary.annotations.Warning;
import com.artlite.bslibrary.helpers.abs.BSBaseHelper;
import com.artlite.bslibrary.managers.BSContextManager;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.WRITE_CONTACTS;

/**
 * Class which provide the contact functionality
 */
public final class BSContactHelper extends BSBaseHelper {

    /**
     * Class which provide the functionality of building of the edit contact intent
     */
    public static class ContactIntentBuilder {

        /**
         * Instance of the {@link Intent}
         */
        private final Intent intent;

        public ContactIntentBuilder() {
            this.intent = new Intent(ContactsContract.Intents.Insert.ACTION);
            this.intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
            if (Integer.valueOf(Build.VERSION.SDK) > 14) {
                intent.putExtra("finishActivityOnSaveCompleted", true);
            }
        }

        /**
         * Method which provide the setting of the {@link Object}
         *
         * @param value instance of the {@link Object}
         * @return instance of the {@link ContactIntentBuilder}
         */
        @NonNull
        public final ContactIntentBuilder setEmail(@Nullable String value) {
            if (!isEmpty(value)) {
                this.intent.putExtra(ContactsContract.Intents.Insert.EMAIL, value)
                        .putExtra(ContactsContract.Intents.Insert.EMAIL_TYPE,
                                ContactsContract.CommonDataKinds.Email.TYPE_WORK);
            }
            return this;
        }

        /**
         * Method which provide the setting of the {@link Object}
         *
         * @param value instance of the {@link Object}
         * @return instance of the {@link ContactIntentBuilder}
         */
        @NonNull
        public final ContactIntentBuilder setPhone(@Nullable String value) {
            if (!isEmpty(value)) {
                this.intent.putExtra(ContactsContract.Intents.Insert.PHONE, value)
                        .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE,
                                ContactsContract.CommonDataKinds.Phone.TYPE_MAIN);
            }
            return this;
        }

        /**
         * Method which provide the setting of the {@link Object}
         *
         * @param value instance of the {@link Object}
         * @return instance of the {@link ContactIntentBuilder}
         */
        @NonNull
        public final ContactIntentBuilder setName(@Nullable String value) {
            if (!isEmpty(value)) {
                this.intent.putExtra(ContactsContract.Intents.Insert.NAME, value);
            }
            return this;
        }

        /**
         * Method which provide the setting of the {@link Object}
         *
         * @param value instance of the {@link Object}
         * @return instance of the {@link ContactIntentBuilder}
         */
        @NonNull
        public final ContactIntentBuilder setCompany(@Nullable String value) {
            if (!isEmpty(value)) {
                this.intent.putExtra(ContactsContract.Intents.Insert.COMPANY, value);
            }
            return this;
        }

        /**
         * Method which provide the setting of the {@link Object}
         *
         * @param value instance of the {@link Object}
         * @return instance of the {@link ContactIntentBuilder}
         */
        @NonNull
        public final ContactIntentBuilder setNotes(@Nullable String value) {
            if (!isEmpty(value)) {
                this.intent.putExtra(ContactsContract.Intents.Insert.NOTES, value);
            }
            return this;
        }

        /**
         * Method which provide the setting of the {@link Object}
         *
         * @param value instance of the {@link Object}
         * @return instance of the {@link ContactIntentBuilder}
         */
        @NonNull
        public final ContactIntentBuilder setJobTitle(@Nullable String value) {
            if (!isEmpty(value)) {
                this.intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, value);
            }
            return this;
        }

        /**
         * Method which provide the building of the {@link Intent}
         *
         * @return instance of the {@link Intent}
         */
        @NonNull
        public final Intent build() {
            return this.intent;
        }
    }

    /**
     * Class which provide the create of the contact builder
     */
    public static class ContactBuilder {

        /**
         * {@link String} constants of the tag
         */
        private static final String TAG = ContactBuilder.class.getSimpleName();

        /**
         * Instance of the {@link Context}
         */
        private final Context context;

        /**
         * Instance of the {@link List}
         */
        private final ArrayList contentProviderOperations;

        /**
         * Method which provide the creating of the {@link ContactBuilder}
         *
         * @param context instance of the {@link Context}
         */
        public ContactBuilder(Context context) {
            this.context = context;
            this.contentProviderOperations = new ArrayList();
            //insert raw contact using RawContacts.CONTENT_URI
            this.contentProviderOperations.add(ContentProviderOperation
                    .newInsert(ContactsContract.RawContacts.CONTENT_URI)
                    .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                    .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                    .build());
        }

        /**
         * Method which provide the setting of the {@link Object}
         *
         * @param value instance of the {@link Object}
         * @return instance of the {@link ContactBuilder}
         */
        public ContactBuilder setName(@NonNull String value) {
            if (value != null) {
                //insert contact display name using Data.CONTENT_URI
                contentProviderOperations.add(ContentProviderOperation
                        .newInsert(ContactsContract.Data.CONTENT_URI)
                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                        .withValue(ContactsContract.Data.MIMETYPE,
                                ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
                                value)
                        .build());
            }
            return this;
        }

        /**
         * Method which provide the setting of the {@link Object}
         *
         * @param value instance of the {@link Object}
         * @return instance of the {@link ContactBuilder}
         */
        public ContactBuilder setNumber(@NonNull String value) {
            if (value != null) {
                //insert mobile number using Data.CONTENT_URI
                contentProviderOperations.add(ContentProviderOperation
                        .newInsert(ContactsContract.Data.CONTENT_URI)
                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                        .withValue(ContactsContract.Data.MIMETYPE,
                                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, value)
                        .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
                                ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                        .build());
            }
            return this;
        }

        /**
         * Method which provide the setting of the {@link Object}
         *
         * @param value instance of the {@link Object}
         * @return instance of the {@link ContactBuilder}
         */
        public ContactBuilder setCompany(@NonNull String value) {
            if (value != null) {
                //insert mobile number using Data.CONTENT_URI
                contentProviderOperations.add(ContentProviderOperation
                        .newInsert(ContactsContract.Data.CONTENT_URI)
                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                        .withValue(ContactsContract.Data.MIMETYPE,
                                ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.Organization.COMPANY, value)
                        .build());
            }
            return this;
        }

        /**
         * Method which provide the setting of the {@link Object}
         *
         * @param value instance of the {@link Object}
         * @return instance of the {@link ContactBuilder}
         */
        public ContactBuilder setNote(@NonNull String value) {
            if (value != null) {
                //insert mobile number using Data.CONTENT_URI
                contentProviderOperations.add(ContentProviderOperation
                        .newInsert(ContactsContract.Data.CONTENT_URI)
                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                        .withValue(ContactsContract.Data.MIMETYPE,
                                ContactsContract.CommonDataKinds.Note.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.Note.NOTE, value)
                        .build());
            }
            return this;
        }

        /**
         * Method which provide the setting of the {@link Object}
         *
         * @param value instance of the {@link Object}
         * @return instance of the {@link ContactBuilder}
         */
        public ContactBuilder setEmail(@NonNull String value) {
            if (value != null) {
                //insert mobile number using Data.CONTENT_URI
                contentProviderOperations.add(ContentProviderOperation
                        .newInsert(ContactsContract.Data.CONTENT_URI)
                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                        .withValue(ContactsContract.Data.MIMETYPE,
                                ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.Email.ADDRESS, value)
                        .build());
            }
            return this;
        }

        /**
         * Method which provide the applying of the batch
         *
         * @return instance of the {@link Boolean}
         */
        @SuppressLint("MissingPermission")
        @RequiresPermission(allOf = {READ_CONTACTS, WRITE_CONTACTS})
        public boolean apply() {
            try {
                this.context.getContentResolver().applyBatch(ContactsContract.AUTHORITY,
                        this.contentProviderOperations);
                return true;
            } catch (Exception ex) {
                Log.e(TAG, "apply: ", ex);
            }
            return false;
        }

    }

    /**
     * Method which provide the creating of the instance of the {@link ContactIntentBuilder}
     *
     * @return instance of the {@link ContactIntentBuilder}
     */
    @NonNull
    public static ContactIntentBuilder createContactIntentBuilder() {
        return new ContactIntentBuilder();
    }

    /**
     * Method which provide the creating of the instance of the {@link ContactIntentBuilder}
     */
    @Warning(massage = "For this method you need to initialize of the BSINStance")
    @NonNull
    public static ContactBuilder createContactBuilder() {
        return new ContactBuilder(BSContextManager.getApplicationContext());
    }

    /**
     * Method which provide the creating of the instance of the {@link ContactIntentBuilder}
     *
     * @return instance of the {@link ContactIntentBuilder}
     */
    @NonNull
    public static ContactBuilder createContactBuilder(@NonNull Context context) {
        return new ContactBuilder(context);
    }

}
