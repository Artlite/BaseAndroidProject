package com.artlite.bslibrary.helpers.contact;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.artlite.bslibrary.helpers.abs.BSBaseHelper;

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
     * Method which provide the creating of the instance of the {@link ContactIntentBuilder}
     *
     * @return instance of the {@link ContactIntentBuilder}
     */
    @NonNull
    public static ContactIntentBuilder create() {
        return new ContactIntentBuilder();
    }

}
