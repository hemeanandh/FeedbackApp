export class FeedbackEntries {


   // public fb_id :Number;
    constructor (public fb_src_id: Number,
    public fb_target_id :Number,
    public fb_attr_id :Number,
    public feedback_entriescol :String,
    public fb_desc?: String,
    ) {}
}