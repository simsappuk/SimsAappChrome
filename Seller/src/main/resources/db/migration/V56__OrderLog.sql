CREATE TABLE public.order_log
(
 id CHARACTER VARYING(255),
 check_out_status CHARACTER VARYING(255),
 payment_method CHARACTER VARYING(255),
 payment_instrument CHARACTER VARYING(255),
 created_time timestamp,
 address_street1 CHARACTER VARYING(255),
 address_street2 CHARACTER VARYING(255),
 address_city CHARACTER VARYING(255),
 address_phone CHARACTER VARYING(255),
 address_postal_code CHARACTER VARYING(255),
 address_id CHARACTER VARYING(255),
 address_state CHARACTER VARYING(255),
 shipping_service CHARACTER VARYING(255),
 paid_time timestamp,
 title CHARACTER VARYING(255),
 item_id CHARACTER VARYING(255),
 order_id CHARACTER VARYING(255),
 buyer_email_id CHARACTER VARYING(255),
 selling_record_number CHARACTER VARYING(255),
 buyer_name CHARACTER VARYING(255),
 shipped_to_country CHARACTER VARYING(255),
 order_status CHARACTER VARYING(255),
 original_item_price CHARACTER VARYING(255),
 seller_email CHARACTER VARYING(255),
 buyer_user_id CHARACTER VARYING(255),
 sku CHARACTER VARYING(255),
 seller_user_id CHARACTER VARYING(255),
 cancel_status CHARACTER VARYING(255),
 extended_order_id CHARACTER VARYING(255),
 total_amount CHARACTER VARYING(255),
 adjustment_amount CHARACTER VARYING(255),
 amount_paid CHARACTER VARYING(255),
 transaction_id CHARACTER VARYING(255),
 transaction_date timestamp,
 last_modified_date timestamp,
 item_discount_amount CHARACTER VARYING(255),
 sub_total CHARACTER VARYING(255),
 total_amount_currency_id CHARACTER VARYING(255),
 sub_amount_currency_id CHARACTER VARYING(255),
 condition_display_name CHARACTER VARYING(255),
 quantity_purchased CHARACTER VARYING(255),
 vat_percent CHARACTER VARYING(255),
 grand_total CHARACTER VARYING(255),
 item_discount_price CHARACTER VARYING(255),
 shipping_service_cost CHARACTER VARYING(255),
 shipping_service_cost_currency_id CHARACTER VARYING(255),
 transaction_price CHARACTER VARYING(255),
 grand_total_currency_id CHARACTER VARYING(255),
 original_item_price_currency_id CHARACTER VARYING(255),
 discount_price_currency_id CHARACTER VARYING(255),
 adjustment_amount_currency_id CHARACTER VARYING(255),
 transaction_price_currency_id CHARACTER VARYING(255),
 item_image CHARACTER VARYING(255),
 order_state CHARACTER VARYING(255)
);